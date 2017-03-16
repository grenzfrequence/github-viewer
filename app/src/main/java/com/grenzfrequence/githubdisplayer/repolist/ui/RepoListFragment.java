package com.grenzfrequence.githubdisplayer.repolist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.base.BaseFragment;
import com.grenzfrequence.githubdisplayer.databinding.RepoListFragmentBinding;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListViewModel;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class RepoListFragment
        extends BaseFragment<RepoListFragmentBinding, RepoListViewModel>
        implements IRefreshableView {

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
        return bindViewModel(inflater, container, R.layout.repo_list_fragment);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        binding.rvRepoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvRepoList.setAdapter(viewModel.getAdapter());

        binding.srlRefresh.setOnRefreshListener(() -> viewModel.loadData());

        viewModel.setShowList(false);
        viewModel.setShowPlaceholder(false);
        showRefresh(false);
    }

    @Override
    public void showRefresh(boolean show) {
        if (show) {
            viewModel.setShowList(false);
            viewModel.setShowPlaceholder(false);
        }
        binding.srlRefresh.post(() -> binding.srlRefresh.setRefreshing(show));
    }

    @Override
    public void onRefreshed(Boolean success) {
        showRefresh(false);
        viewModel.setShowList(success);
        viewModel.setShowPlaceholder(!success);
    }

}
