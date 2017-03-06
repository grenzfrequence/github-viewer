package com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.grenzfrequence.githubdisplayer.GitHubDisplayerApplication;
import com.grenzfrequence.githubdisplayer.databinding.RepoListItemBinding;
import com.grenzfrequence.githubdisplayer.di.ApplicationComponent;
import com.grenzfrequence.githubdisplayer.di.HolderScope;
import com.grenzfrequence.githubdisplayer.repolist.RepoListItemViewModel;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListItem;
import com.grenzfrequence.githubdisplayer.repolist.di.DaggerRepoListItemViewHolderComponent;
import com.grenzfrequence.githubdisplayer.repolist.di.RepoListItemViewHolderModule;
import com.grenzfrequence.githubdisplayer.ui.Holder;

import javax.inject.Inject;

/**
 * Created by grenzfrequence on 04/03/17.
 */
@HolderScope
public class RepoListItemHolder extends RecyclerView.ViewHolder implements Holder<RepoListItem> {

    private RepoListItemBinding repoListItemBinding;

    @Inject
    RepoListItemViewModel repoListViewModel;

    RepoListItemHolder(View view) {
        super(view);

        ApplicationComponent applicationComponent = GitHubDisplayerApplication
                .get(view.getContext())
                .getApplicationComponent();
        DaggerRepoListItemViewHolderComponent
                .builder()
                .applicationComponent(applicationComponent)
                .repoListItemViewHolderModule(new RepoListItemViewHolderModule())
                .build()
                .inject(this);

        repoListItemBinding = DataBindingUtil.bind(view);
        repoListItemBinding.setViewModel(repoListViewModel);
    }

    @Override
    public void setData(RepoListItem repoListItem) {
        repoListViewModel.setRepoListItem(repoListItem);
        repoListItemBinding.executePendingBindings();
    }
}
