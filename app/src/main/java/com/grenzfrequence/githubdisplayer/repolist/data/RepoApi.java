package com.grenzfrequence.githubdisplayer.repolist.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by grenzfrequence on 03/03/17.
 */

public interface RepoApi {

    // http://api.github.com/users/{user}/repos?page=2&per_page=2
    @GET("users/{user}/repos")
    @NonNull
    Observable<Response<List<RepoModel>>> getRepoList(
            @Path("user") String userName,
            @Query("page") int pageNr,
            @Query("per_page") int itemsPerPage,
            @Query("sort") String sortField);

}
