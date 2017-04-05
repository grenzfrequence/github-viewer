package com.grenzfrequence.githubdisplayer.repolist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.base.BaseFragment;
import com.grenzfrequence.githubdisplayer.common.ui.IRefreshableRecyclerView;
import com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter.EndlessRecyclerViewScrollListener;
import com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter.RecyclerBindingAdapter;
import com.grenzfrequence.githubdisplayer.databinding.RepoListFragmentBinding;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListItemViewModel;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListViewModel;
import com.grenzfrequence.githubdisplayer.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class RepoListFragment
        extends BaseFragment<RepoListFragmentBinding, RepoListViewModel>
        implements IRefreshableRecyclerView<RepoModel> {

    private static final int     FIRST_PAGE    = 1;
    private static final boolean RESET_DATA    = true;
    private static final boolean NO_RESET_DATA = false;

    @Inject
    RecyclerBindingAdapter<RepoModel, RepoListItemViewModel> repoRecyclerAdapter;

    LinearLayoutManager               linearLayoutManager;
    EndlessRecyclerViewScrollListener recyclerViewScrollListener;

    Disposable onLoadMoreSubscription = null;
    Disposable loadDataSubscription   = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = bindViewModel(inflater, container, R.layout.repo_list_fragment);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.rvRepoList.setLayoutManager(linearLayoutManager);
        binding.rvRepoList.setAdapter(repoRecyclerAdapter);
        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                int pageNr = page + 1;
                if (viewModel.isMoreData(pageNr)) {
                    onLoadMoreSubscription = Observable
                            .defer(() -> Observable.just(repoRecyclerAdapter.showOnLoadMore()))
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe();
                }
                loadDataSubscription = Observable
                        .defer(() -> Observable.just(viewModel.loadData(pageNr, NO_RESET_DATA)))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            }
        };
        binding.rvRepoList.addOnScrollListener(recyclerViewScrollListener);
        binding.srlRefresh.setOnRefreshListener(() -> viewModel.loadData(FIRST_PAGE, RESET_DATA));
        onRefresh(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        RxUtils.dispose(onLoadMoreSubscription);
        RxUtils.dispose(loadDataSubscription);
    }

    @Override
    public void onRefresh(boolean refreshInProgress) {
        if (refreshInProgress) {
            viewModel.setShowList(false);
            viewModel.setShowPlaceholder(false);
        }
        binding.srlRefresh.post(() -> binding.srlRefresh.setRefreshing(refreshInProgress));
    }

    @Override
    public void onRefreshed(Boolean success) {
        onRefresh(false);
        viewModel.setShowList(success);
        viewModel.setShowPlaceholder(!success);
    }

    @Override
    public void setListItems(List<RepoModel> listItems, boolean resetData) {
        repoRecyclerAdapter.setListItems(listItems);
        if (resetData) {
            recyclerViewScrollListener.resetState();
        }
    }

}
