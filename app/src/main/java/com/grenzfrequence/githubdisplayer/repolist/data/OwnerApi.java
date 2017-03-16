package com.grenzfrequence.githubdisplayer.repolist.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by grenzfrequence on 16/03/17.
 */

public interface OwnerApi {

    // https://api.github.com/users/{user}
    @GET("users/{userName}")
    @Nullable
    Observable<Response<OwnerModel>> getOwner(@Path("userName") @NonNull String userName);
}
