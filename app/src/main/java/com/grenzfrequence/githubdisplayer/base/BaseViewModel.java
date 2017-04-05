package com.grenzfrequence.githubdisplayer.base;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

/**
 * Created by grenzfrequence on 11/03/17.
 */

public abstract class BaseViewModel<VIEW extends MvvmView, ITEM> extends BaseObservable
        implements MvvmViewModel<VIEW, ITEM> {

    private VIEW view;

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

}
