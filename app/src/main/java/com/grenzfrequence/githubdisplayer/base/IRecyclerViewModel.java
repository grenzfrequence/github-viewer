package com.grenzfrequence.githubdisplayer.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by grenzfrequence on 07/03/17.
 */

public interface IRecyclerViewModel<VIEW extends MvvmView> extends ILoaderViewModel<VIEW> {
    RecyclerView.Adapter getAdapter();
}
