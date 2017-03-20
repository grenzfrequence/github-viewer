package com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.grenzfrequence.githubdisplayer.base.MvvmViewModel;
import com.grenzfrequence.githubdisplayer.di.scopes.ViewHolderScope;

/**
 * Created by grenzfrequence on 06/03/17.
 */

@ViewHolderScope
public class BindingViewHolder<VIEWMODEL extends MvvmViewModel>
        extends RecyclerView.ViewHolder {

    private ViewDataBinding itemBinding;

    protected VIEWMODEL viewModel;

    public BindingViewHolder(View itemView, VIEWMODEL viewModel, @IdRes int viewModelId) {
        super(itemView);
        this.viewModel = viewModel;
        bindViewModel(viewModelId);
    }

    protected void bindViewModel(@IdRes int viewModelId) {
        itemBinding = DataBindingUtil.bind(itemView);
        itemBinding.setVariable(viewModelId, viewModel);
    }

    public VIEWMODEL getViewModel() {
        return viewModel;
    }

    public ViewDataBinding getItemBinding() {
        return itemBinding;
    }

    public void executePendingBindings() {
        if (viewModel != null && itemBinding != null) {
            itemBinding.executePendingBindings();
        }
    }

}
