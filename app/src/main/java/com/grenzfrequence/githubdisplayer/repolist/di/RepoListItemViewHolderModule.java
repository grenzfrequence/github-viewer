package com.grenzfrequence.githubdisplayer.repolist.di;

import android.support.annotation.NonNull;

import com.grenzfrequence.githubdisplayer.di.ActivityScope;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 03/03/17.
 */

@Module
@ActivityScope
public class RepoListItemViewHolderModule {

    @Provides
    @NonNull
    RepoListService provideRepoListService(@NonNull Retrofit retrofit) {
        return retrofit.create(RepoListService.class);
    }

}
