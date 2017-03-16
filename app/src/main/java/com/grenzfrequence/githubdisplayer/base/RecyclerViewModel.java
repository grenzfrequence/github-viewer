package com.grenzfrequence.githubdisplayer.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by grenzfrequence on 09/03/17.
 */

public abstract class RecyclerViewModel<VIEW extends MvvmView, ADAPTER extends RecyclerView.Adapter>
        extends BaseViewModel
        implements IRecyclerViewModel<VIEW> {

    private VIEW    view;
    private ADAPTER adapter;

    public RecyclerViewModel(ADAPTER adapter, IResourceValues recAccess) {
        super(recAccess);
        this.adapter = adapter;
    }

    @Override
    public void attachView(@NonNull VIEW view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    @NonNull
    public VIEW getView() {
        return view;
    }

    @Override
    @NonNull
    public ADAPTER getAdapter() {
        return adapter;
    }

}
