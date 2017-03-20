package com.grenzfrequence.githubdisplayer.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created by grenzfrequence on 09/03/17.
 */

public abstract class BaseRecyclerViewModel<VIEW extends MvvmView, ADAPTER extends RecyclerView.Adapter, ITEM>
        extends BaseViewModel<VIEW, ITEM>
        implements IRecyclerViewModel<VIEW, ITEM> {

    private ADAPTER adapter;

    public BaseRecyclerViewModel(ADAPTER adapter) {
        this.adapter = adapter;
    }

    @Override
    @NonNull
    public ADAPTER getAdapter() {
        return adapter;
    }

}
