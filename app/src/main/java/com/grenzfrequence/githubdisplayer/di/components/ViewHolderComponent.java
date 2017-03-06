package com.grenzfrequence.githubdisplayer.di.components;

import android.support.annotation.NonNull;

import com.grenzfrequence.githubdisplayer.di.modules.ViewModelModul;
import com.grenzfrequence.githubdisplayer.di.scopes.ViewHolderScope;
import com.grenzfrequence.githubdisplayer.repolist.RepoListItemViewModel;
import com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview.RepoListItemViewHolder;

import dagger.Component;

/**
 * Created by grenzfrequence on 06/03/17.
 */

@Component(modules = {ViewModelModul.class}, dependencies = AppComponent.class)
@ViewHolderScope
public interface ViewHolderComponent {

    void inject(RepoListItemViewHolder viewHolder);

    @NonNull
    RepoListItemViewModel getRepoListViewModel();

}
