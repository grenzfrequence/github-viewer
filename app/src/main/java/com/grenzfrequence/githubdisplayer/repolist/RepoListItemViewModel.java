package com.grenzfrequence.githubdisplayer.repolist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListItem;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListModel;

import javax.inject.Inject;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class RepoListItemViewModel extends BaseObservable {

    private RepoListItem  repoListItem;
    private RepoListModel repoListModel;

    @Inject
    public RepoListItemViewModel(RepoListModel repoListModel) {
        super();
        this.repoListModel = repoListModel;
    }

    public void setRepoListItem(RepoListItem repoListItem) {
        this.repoListItem = repoListItem;
        notifyPropertyChanged(BR.repoName);
//        notifyChange();
    }

    @Bindable
    public String getRepoName() {
        return repoListItem.repoName();
    }

}
