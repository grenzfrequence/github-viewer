package com.grenzfrequence.githubdisplayer.di.modules;

import android.support.annotation.NonNull;

import com.grenzfrequence.githubdisplayer.di.scopes.ViewHolderScope;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 06/03/17.
 */

@Module
public class ViewModelModul {

    //
    // ViewHolders
    //

    @Provides
    @ViewHolderScope
    @NonNull
    RepoListService provideRepoListService(@NonNull Retrofit retrofit) {
        return retrofit.create(RepoListService.class);
    }

}
