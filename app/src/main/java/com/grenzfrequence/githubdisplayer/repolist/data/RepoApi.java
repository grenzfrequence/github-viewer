package com.grenzfrequence.githubdisplayer.repolist.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by grenzfrequence on 03/03/17.
 */

public interface RepoApi {

    // https://api.github.com/users/{user}/repos
    @GET("users/{user}/repos")
    @NonNull
    Observable<Response<List<RepoModel>>> getRepoList(@Path("user") String userName);

}
