package com.grenzfrequence.githubdisplayer.repolist.data;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class RepoListModel {

    RepoListService restApiServices;

    @Inject
    public RepoListModel(@NonNull RepoListService repoListRestApi) {
        this.restApiServices = repoListRestApi;
    }

    @NonNull
    public Observable<List<RepoListItem>> getRepoListItems(String user) {
        return restApiServices.getRepoListItems(user);
    }

}
