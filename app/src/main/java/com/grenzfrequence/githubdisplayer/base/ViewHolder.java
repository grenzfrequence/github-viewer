package com.grenzfrequence.githubdisplayer.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.grenzfrequence.githubdisplayer.Application;
import com.grenzfrequence.githubdisplayer.di.components.AppComponent;
import com.grenzfrequence.githubdisplayer.di.components.DaggerViewHolderComponent;
import com.grenzfrequence.githubdisplayer.di.components.ViewHolderComponent;
import com.grenzfrequence.githubdisplayer.di.modules.ViewModelModul;
import com.grenzfrequence.githubdisplayer.di.scopes.ViewHolderScope;

import javax.inject.Inject;

/**
 * Created by grenzfrequence on 06/03/17.
 */

@ViewHolderScope
public class ViewHolder<VIEWMODEL, BINDING extends ViewDataBinding>
        extends RecyclerView.ViewHolder {

    private ViewHolderComponent viewHolderComponent;
    private BINDING             itemBinding;

    @Inject
    VIEWMODEL viewModel;

    public ViewHolder(View itemView) {
        super(itemView);
    }

    protected ViewHolderComponent getComponent() {
        if (viewHolderComponent == null) {
            AppComponent applicationComponent = Application.get(itemView.getContext()).getApplicationComponent();
            viewHolderComponent = DaggerViewHolderComponent
                    .builder()
                    .appComponent(applicationComponent)
                    .viewModelModul(new ViewModelModul())
                    .build();
        }
        return viewHolderComponent;
    }

    protected void bindViewModel() {
        itemBinding = DataBindingUtil.bind(itemView);
        itemBinding.setVariable(BR.viewModel, viewModel);
    }

    public VIEWMODEL getViewModel() {
        return viewModel;
    }

    public void executePendingBindings() {
        if (viewModel != null && itemBinding != null) {
            itemBinding.executePendingBindings();
        }
    }

}
