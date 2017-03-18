package com.grenzfrequence.githubdisplayer.di.components;

import android.support.annotation.NonNull;

import com.grenzfrequence.githubdisplayer.di.modules.ViewModelModule;
import com.grenzfrequence.githubdisplayer.di.scopes.ViewHolderScope;
import com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview.RepoListItemViewHolder;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListItemViewModel;

import dagger.Component;

/**
 * Created by grenzfrequence on 06/03/17.
 */

@Component(modules = {ViewModelModule.class}, dependencies = ActivityComponent.class)
@ViewHolderScope
public interface ViewHolderComponent {

    void inject(RepoListItemViewHolder viewHolder);

    @NonNull
    RepoListItemViewModel getRepoListItemViewModel();

}
