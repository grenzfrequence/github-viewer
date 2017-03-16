package com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview;

import android.view.View;

import com.grenzfrequence.githubdisplayer.base.BaseViewHolder;
import com.grenzfrequence.githubdisplayer.databinding.RepoListItemBinding;
import com.grenzfrequence.githubdisplayer.di.scopes.ViewHolderScope;
import com.grenzfrequence.githubdisplayer.repolist.viewmodel.RepoListItemViewModel;

/**
 * Created by grenzfrequence on 06/03/17.
 */

@ViewHolderScope
public class RepoListItemViewHolder
        extends BaseViewHolder<RepoListItemViewModel, RepoListItemBinding> {

    public RepoListItemViewHolder(View itemView) {
        super(itemView);
        getComponent().inject(this);
        bindViewModel();
    }

}
