package com.grenzfrequence.githubdisplayer.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grenzfrequence.githubdisplayer.BR;
import com.grenzfrequence.githubdisplayer.di.components.FragmentComponent;
import com.grenzfrequence.githubdisplayer.utils.ComponentManager;

import javax.inject.Inject;


/**
 * Created by grenzfrequence on 08/03/17.
 */

public class BaseFragment<BINDING extends ViewDataBinding, VIEWMODEL extends MvvmViewModel>
        extends Fragment {

    @Inject
    protected VIEWMODEL viewModel;
    protected BINDING   binding;

    private FragmentComponent fragmentComponent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //noinspection unchecked
        viewModel.attachView((MvvmView) this);
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.detachView();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            viewModel.restoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            viewModel.saveInstanceState(outState);
        }
    }

    protected final FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentManager
                    .getFragmentComponent(((BaseActivity) getActivity()).getActivityComponent());
        }
        return fragmentComponent;
    }

    protected View bindViewModel(
            LayoutInflater layoutInflater,
            @Nullable ViewGroup container,
            @LayoutRes int layoutResId) {

        binding = DataBindingUtil.inflate(layoutInflater, layoutResId, null, false);
        binding.setVariable(BR.viewModel, viewModel);

        return binding.getRoot();
    }

}
