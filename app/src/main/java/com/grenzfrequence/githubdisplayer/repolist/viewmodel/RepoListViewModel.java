package com.grenzfrequence.githubdisplayer.repolist.viewmodel;

import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.base.BaseRecyclerViewModel;
import com.grenzfrequence.githubdisplayer.common.ErrMsg;
import com.grenzfrequence.githubdisplayer.common.ui.IRefreshableView;
import com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter.RecyclerBindingAdapter;
import com.grenzfrequence.githubdisplayer.di.qualifiers.UiErrMsgQualifier;
import com.grenzfrequence.githubdisplayer.global.HttpResponses;
import com.grenzfrequence.githubdisplayer.repolist.data.OwnerModel;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;
import com.grenzfrequence.githubdisplayer.utils.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by grenzfrequence on 07/03/17.
 */

public class RepoListViewModel
        extends BaseRecyclerViewModel<
        IRefreshableView,
        RecyclerBindingAdapter<RepoModel, RepoListItemViewModel>,
        String> {

    private static final String KEY_USER_NAME = "UserName";

    @UiErrMsgQualifier
    private Map<Integer, ErrMsg> errMessages;

    private RepoApi repoListApi;

    private      String                  userName          = "";
    public final ObservableField<String> avatarUrl         = new ObservableField<>(null);
    public final ObservableBoolean       showList          = new ObservableBoolean(false);
    public final ObservableBoolean       showPlaceholder   = new ObservableBoolean(false);
    public final ObservableInt           errorMessageId    = new ObservableInt();
    public final ObservableInt           placeHolderIconId = new ObservableInt(R.drawable.ic_info_outline_black);

    private Disposable subscription;

    @Inject
    public RepoListViewModel(
            RepoApi repoListApi,
            @UiErrMsgQualifier Map<Integer, ErrMsg> errMessages,
            RecyclerBindingAdapter<RepoModel, RepoListItemViewModel> adapter) {
        super(adapter);
        this.repoListApi = repoListApi;
        this.errMessages = errMessages;
        errorMessageId.set(errMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT).getErrorMessageId());
    }

    @Override
    public void setData(String userName) {
        setUserName(userName);
    }

    @Bindable
    public void setUserName(String userName) {
        this.userName = userName;
        if (ObjectUtils.isNullOrEmpty(userName)) {
            dispose();
            getView().onRefreshed(false);
            return;
        }
        loadData();
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
        dispose();
    }

    @Override
    public void loadData() {
        getView().showRefresh(true);

        dispose();
        subscription = repoListApi
                .getRepoList(userName)
                .map(response -> {
                    List<RepoModel> repoList = response.body();
                    Collections.sort(repoList);
                    return response;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        repoListItemsResponse -> {
                            List<RepoModel> repoListItems = repoListItemsResponse.body();
                            updateUi(repoListItems);
                            handleBusinessCaseErrors(
                                    repoListItemsResponse,
                                    !ObjectUtils.isNullOrEmpty(repoListItems));
                        }, throwable -> {
                            updateUi(null);
                            getView().onRefreshed(false);

                            ErrMsg errMsg = errMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT);
                            placeHolderIconId.set(errMsg.getErrorIconId());
                            errorMessageId.set(errMsg.getErrorMessageId());
                        }
                );
    }

    private void updateUi(List<RepoModel> repoListItems) {
        getAdapter().setListItems(repoListItems);
        getAdapter().notifyDataSetChanged();

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

    private void dispose() {
        if (subscription != null) {
            subscription.dispose();
            subscription = null;
        }
    }


}
