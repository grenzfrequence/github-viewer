package com.grenzfrequence.githubdisplayer.repolist.data;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by grenzfrequence on 03/03/17.
 */

public interface RepoListService {

    // https://api.github.com/users/grenzfrequence/repos
    @GET("users/{user}/repos")
    @NonNull
    Observable<List<RepoListItem>> getRepoListItems(@Path("user") String user);

}
