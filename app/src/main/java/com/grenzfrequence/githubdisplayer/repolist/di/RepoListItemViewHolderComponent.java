package com.grenzfrequence.githubdisplayer.repolist.di;

import android.support.annotation.NonNull;

import com.grenzfrequence.githubdisplayer.di.ApplicationComponent;
import com.grenzfrequence.githubdisplayer.di.HolderScope;
import com.grenzfrequence.githubdisplayer.repolist.RepoListItemViewModel;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListModel;
import com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview.RepoListItemHolder;

import dagger.Component;

/**
 * Created by grenzfrequence on 03/03/17.
 */

@Component(modules = RepoListItemViewHolderModule.class, dependencies = ApplicationComponent.class)
@HolderScope
public interface RepoListItemViewHolderComponent {

    void inject(RepoListItemHolder repoListViewModel);

    @NonNull
    RepoListModel getRepoListModel();

    @NonNull
    RepoListItemViewModel getRepoListViewModel();


//    @Subcomponent.Builder
//    interface Builder extends SubComponentBuilder<RepoListComponent> {
//        Builder repoListRestApiComponent(RepoListRestApiServices repoListRestApiServices);
//    }

}
