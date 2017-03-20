package com.grenzfrequence.githubdisplayer.base;

import android.databinding.Observable;

/**
 * Created by grenzfrequence on 07/03/17.
 */

public interface ILoaderViewModel<VIEW extends MvvmView, ITEM> extends MvvmViewModel<VIEW, ITEM>, Observable {

    void loadData();
}
