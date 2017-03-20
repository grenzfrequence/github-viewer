package com.grenzfrequence.githubdisplayer.di.components;

import com.grenzfrequence.githubdisplayer.base.IResourceValues;
import com.grenzfrequence.githubdisplayer.common.ErrMsg;
import com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter.RecyclerBindingAdapter;
import com.grenzfrequence.githubdisplayer.di.modules.FragmentModule;
import com.grenzfrequence.githubdisplayer.di.qualifiers.UiErrMsgQualifier;
import com.grenzfrequence.githubdisplayer.di.scopes.FragmentScope;
import com.grenzfrequence.githubdisplayer.repolist.data.OwnerApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;
import com.grenzfrequence.githubdisplayer.repolist.ui.RepoListFragment;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListItemViewModel;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListViewModel;

import java.util.Map;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 08/03/17.
 */

@Component(modules = {FragmentModule.class}, dependencies = ActivityComponent.class)
@FragmentScope
public interface FragmentComponent {

    void inject(RepoListFragment repoListFragment);

    Retrofit retrofit();

    RepoApi repoListApi();

    OwnerApi ownerApi();

    RepoListViewModel repoListViewModel();

    RecyclerBindingAdapter<RepoModel, RepoListItemViewModel> repoListViewAdapter();

    IResourceValues resourceValues();

    @UiErrMsgQualifier
    Map<Integer, ErrMsg> errMessages();
}
