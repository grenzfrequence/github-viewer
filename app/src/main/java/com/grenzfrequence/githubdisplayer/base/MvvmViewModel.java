package com.grenzfrequence.githubdisplayer.base;

import android.databinding.Observable;
import android.os.Bundle;

/**
 * Created by grenzfrequence on 08/03/17.
 */

public interface MvvmViewModel<VIEW extends MvvmView> extends Observable {

    void attachView(VIEW view);

    void detachView();

    VIEW getView();

    void restoreInstanceState(Bundle savedInstanceState);

    void saveInstanceState(Bundle outState);

}
