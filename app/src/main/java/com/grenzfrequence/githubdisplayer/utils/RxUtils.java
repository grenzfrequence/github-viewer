package com.grenzfrequence.githubdisplayer.utils;

import io.reactivex.disposables.Disposable;

/**
 * Created by grenzfrequence on 27/03/17.
 */

public class RxUtils {

    public static void dispose(Disposable disposable) {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
