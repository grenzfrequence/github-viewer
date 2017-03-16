package com.grenzfrequence.githubdisplayer.base;

import android.databinding.BaseObservable;

import javax.inject.Inject;

/**
 * Created by grenzfrequence on 11/03/17.
 */

public class BaseViewModel extends BaseObservable {

    protected IResourceValues resAccess;

    @Inject
    public BaseViewModel(IResourceValues recAccess) {
        this.resAccess = recAccess;
    }

}
