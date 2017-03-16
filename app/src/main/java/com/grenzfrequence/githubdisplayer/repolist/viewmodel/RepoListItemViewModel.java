package com.grenzfrequence.githubdisplayer.repolist.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.grenzfrequence.githubdisplayer.repolist.data.OwnerModel;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;

import javax.inject.Inject;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class RepoListItemViewModel extends BaseObservable {

    public final ObservableField<String> ownerName       = new ObservableField<>("");
    public final ObservableField<String> ownerAvatarLink = new ObservableField<>("");

    public final ObservableInt           repoId          = new ObservableInt(0);
    public final ObservableField<String> repoName        = new ObservableField<>("");
    public final ObservableField<String> repoDescription = new ObservableField<>("");

    @Inject
    public RepoListItemViewModel() {
        super();
    }

    public void setRepoListItem(RepoModel repoListItem) {
        OwnerModel owner = repoListItem.repoOwner();
        ownerName.set(owner.name());
        ownerAvatarLink.set(owner.avatarLink());

        repoId.set(repoListItem.repoId());
        repoName.set(repoListItem.repoName());
        repoDescription.set(repoListItem.repoDescription());

        notifyChange();
    }

}
