package com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview;

import android.view.View;

import com.grenzfrequence.githubdisplayer.base.ViewHolder;
import com.grenzfrequence.githubdisplayer.databinding.RepoListItemBinding;
import com.grenzfrequence.githubdisplayer.di.scopes.ViewHolderScope;
import com.grenzfrequence.githubdisplayer.repolist.RepoListItemViewModel;

/**
 * Created by grenzfrequence on 06/03/17.
 */

@ViewHolderScope
public class RepoListItemViewHolder extends ViewHolder<RepoListItemViewModel, RepoListItemBinding> {
    public RepoListItemViewHolder(View itemView) {
        super(itemView);
        getComponent().inject(this);
        bindViewModel();
    }
}
