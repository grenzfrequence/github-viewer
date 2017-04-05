package com.grenzfrequence.githubdisplayer.repolist.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.base.BaseRecyclerViewModel;
import com.grenzfrequence.githubdisplayer.common.ErrMsg;
import com.grenzfrequence.githubdisplayer.common.data.DataPager;
import com.grenzfrequence.githubdisplayer.common.ui.IRefreshableRecyclerView;
import com.grenzfrequence.githubdisplayer.di.qualifiers.UiErrMsgQualifier;
import com.grenzfrequence.githubdisplayer.global.HttpResponses;
import com.grenzfrequence.githubdisplayer.repolist.data.OwnerModel;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;
import com.grenzfrequence.githubdisplayer.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.grenzfrequence.githubdisplayer.utils.RxUtils.dispose;

/**
 * Created by grenzfrequence on 07/03/17.
 */

public class RepoListViewModel
        extends BaseRecyclerViewModel<IRefreshableRecyclerView<RepoModel>, String> {

    private static final String KEY_USER_NAME = "UserName";

    public static final  int    QUERY_FIRST_PAGE     = 1;
    private static final int    QUERY_ITEMS_PER_PAGE = 12;
    private static final String QUERY_UPDATED_SORT   = "updated";

    private static final boolean RESET_DATA = true;

    private static final long TIME_AFTER_DATA_ENTRY = 1;

    @UiErrMsgQualifier
    private Map<Integer, ErrMsg> errMessages;
    private DataPager dataPager = null;

    private RepoApi repoListApi;
    private List<RepoModel> repoListItems = new ArrayList<>();

    private      String                  userName          = "";
    public final ObservableField<String> avatarUrl         = new ObservableField<>(null);
    public final ObservableBoolean       showList          = new ObservableBoolean(false);
    public final ObservableBoolean       showPlaceholder   = new ObservableBoolean(false);
    public final ObservableBoolean       showProgressBar   = new ObservableBoolean(false);
    public final ObservableInt           errorMessageId    = new ObservableInt();
    public final ObservableInt           placeHolderIconId = new ObservableInt(R.drawable.ic_info_outline_black);

    private Disposable loaderSubscription = null;
    private Disposable timerSubscription  = null;

    @Inject
    public RepoListViewModel(
            RepoApi repoListApi,
            @UiErrMsgQualifier Map<Integer, ErrMsg> errMessages,
            DataPager dataPager) {
        this.repoListApi = repoListApi;
        this.errMessages = errMessages;
        this.dataPager = dataPager;
        errorMessageId.set(errMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT).getErrorMessageId());
    }

    @Override
    public void setData(String userName) {
        setUserName(userName);
    }

    @Bindable
    public void setUserName(String userName) {
        dispose(loaderSubscription);
        this.userName = userName;
        if (ObjectUtils.isNullOrEmpty(userName)) {
            getView().onRefreshed(false);
            avatarUrl.set(null);
            return;
        }
        dispose(timerSubscription);
        timerSubscription = Observable
                .timer(TIME_AFTER_DATA_ENTRY, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> loadData(QUERY_FIRST_PAGE, RESET_DATA));
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setShowList(boolean show) {
        this.showList.set(show);
    }

    public void setShowPlaceholder(boolean show) {
        this.showPlaceholder.set(show);
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        setUserName(savedInstanceState.getString(KEY_USER_NAME));
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        outState.putString(KEY_USER_NAME, getUserName());
    }

    @Override
    public void detachView() {
        super.detachView();
        dispose(loaderSubscription);
        dispose(timerSubscription);
    }

    public boolean isMoreData(int pageNr) {
        return dataPager.isMoreData(pageNr);
    }

    @Override
    public int loadData(int pageNr, boolean resetData) {
        dispose(loaderSubscription);
        if (resetData) {
            dataPager.reset();
            getView().onRefresh(true);
        }
        if (!dataPager.isMoreData(pageNr)) {
            return -1;
        }
        loaderSubscription = repoListApi
                .getRepoList(userName, pageNr, QUERY_ITEMS_PER_PAGE, QUERY_UPDATED_SORT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        repoListItemsResponse -> {
                            dataPager.updatePager(repoListItemsResponse.headers());
                            updateUi(repoListItemsResponse.body(), resetData);
                            handleBusinessCaseErrors(
                                    repoListItemsResponse,
                                    !ObjectUtils.isNullOrEmpty(repoListItems));
                        }, throwable -> {
                            updateUi(null, RESET_DATA);
                            getView().onRefreshed(false);
                            ErrMsg errMsg = errMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT);
                            placeHolderIconId.set(errMsg.getErrorIconId());
                            errorMessageId.set(errMsg.getErrorMessageId());
                        }
                );
        return 0;
    }

    private void updateUi(List<RepoModel> repoListItemsFetched, boolean resetData) {
        if (resetData) {
            repoListItems.clear();
        }
        if (repoListItemsFetched != null) {
            repoListItems.addAll(repoListItemsFetched);
        }
        getView().setListItems(repoListItems, resetData);

        if (ObjectUtils.isNullOrEmpty(repoListItems)) {
            avatarUrl.set(null);
            return;
        }
        OwnerModel ownerModel = repoListItems.get(0).repoOwner();
        if (ownerModel == null || ObjectUtils.isNullOrEmpty(ownerModel.avatarLink())) {
            avatarUrl.set(null);
            return;
        }
        avatarUrl.set(ownerModel.avatarLink());
    }

    private <T> void handleBusinessCaseErrors(Response<T> response, boolean isRepoExists) {
        ErrMsg errMsg = null;
        placeHolderIconId.set(R.drawable.ic_info_outline_black);
        int errorCode = response.code();
        if (errorCode != HttpResponses.HTTP_OK) {
            errMsg = errMessages.get(errorCode);
            if (errMsg == null) {
                errMsg = errMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT);
            }
        } else if (!isRepoExists) {
            errMsg = errMessages.get(HttpResponses.HTTP_NOT_FOUND);
        }
        if (errMsg != null) {
            int errorMessageId = errMsg.getErrorMessageId();

            this.errorMessageId.set(errorMessageId);
            placeHolderIconId.set(errMsg.getErrorIconId());
        }
        getView().onRefreshed(errMsg == null);
    }


}
