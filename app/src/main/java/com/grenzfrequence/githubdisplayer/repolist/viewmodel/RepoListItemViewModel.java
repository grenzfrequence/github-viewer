package com.grenzfrequence.githubdisplayer.repolist.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;

import com.grenzfrequence.githubdisplayer.base.BaseViewModel;
import com.grenzfrequence.githubdisplayer.common.ui.IView;
import com.grenzfrequence.githubdisplayer.repolist.data.OwnerModel;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;

import org.joda.time.DateTime;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class RepoListItemViewModel extends BaseViewModel<IView, RepoModel> {

    public final ObservableField<String> ownerName       = new ObservableField<>("");
    public final ObservableField<String> ownerAvatarLink = new ObservableField<>("");

    public final ObservableInt             repoId          = new ObservableInt(0);
    public final ObservableField<String>   repoName        = new ObservableField<>("");
    public final ObservableField<String>   repoDescription = new ObservableField<>("");
    public final ObservableField<DateTime> repoUpdateDate  = new ObservableField<>(null);

    @Override
    public void setData(RepoModel repoListItem) {
        OwnerModel owner = repoListItem.repoOwner();
        ownerName.set(owner.name());
        ownerAvatarLink.set(owner.avatarLink());

        repoId.set(repoListItem.repoId());
        repoName.set(repoListItem.repoName());
        repoDescription.set(repoListItem.repoDescription());
        repoUpdateDate.set(repoListItem.repoUpdateDate());

        notifyChange();
    }

    @Override
    public void restoreInstanceState(Bundle savedInstanceState) {
        // not necessary because data is reloaded
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        // not necessary because data is reloaded
    }
}
