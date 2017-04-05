package com.grenzfrequence.githubdisplayer.di.modules;

import android.support.annotation.NonNull;

import com.android.databinding.library.baseAdapters.BR;
import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.common.ErrMsg;
import com.grenzfrequence.githubdisplayer.common.data.DataPager;
import com.grenzfrequence.githubdisplayer.common.data.GitHubPager;
import com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter.RecyclerBindingAdapter;
import com.grenzfrequence.githubdisplayer.di.qualifiers.UiErrMsgQualifier;
import com.grenzfrequence.githubdisplayer.repolist.data.OwnerApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListItemViewModel;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListViewModel;

import java.util.Map;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 09/03/17.
 */

@Module
public class FragmentModule {

    @Provides
    @NonNull
    RepoApi provideRepoApi(@NonNull Retrofit retrofit) {
        return retrofit.create(RepoApi.class);
    }

    @Provides
    @NonNull
    OwnerApi provideOwnerApi(@NonNull Retrofit retrofit) {
        return retrofit.create(OwnerApi.class);
    }

    @Provides
    @NonNull
    RepoListViewModel provideRepoListViewModel(
            RepoApi repoApi, @UiErrMsgQualifier Map<Integer,
            ErrMsg> errMessages,
            DataPager dataPager) {
        return new RepoListViewModel(repoApi, errMessages, dataPager);
    }

    @Provides
    @NonNull
    DataPager provideGitHubPager() {
        return new GitHubPager();
    }

    @Provides
    @NonNull
    RecyclerBindingAdapter<RepoModel, RepoListItemViewModel> provideRecyclerBindingAdapter() {
        return new RecyclerBindingAdapter<>(
                R.layout.repo_list_item,
                R.layout.recycler_binding_progress_bar,
                BR.viewModel,
                RepoListItemViewModel::new);
    }
}
