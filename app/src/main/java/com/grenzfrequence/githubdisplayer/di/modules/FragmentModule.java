package com.grenzfrequence.githubdisplayer.di.modules;

import android.support.annotation.NonNull;

import com.grenzfrequence.githubdisplayer.repolist.data.OwnerApi;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoApi;

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
}
