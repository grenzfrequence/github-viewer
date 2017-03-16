package com.grenzfrequence.githubdisplayer.base;

import android.databinding.Observable;

/**
 * Created by grenzfrequence on 07/03/17.
 */

public interface ILoaderViewModel<VIEW extends MvvmView> extends MvvmViewModel<VIEW>, Observable {

    void loadData();
}
