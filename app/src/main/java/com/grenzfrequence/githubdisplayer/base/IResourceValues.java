package com.grenzfrequence.githubdisplayer.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.BoolRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;

/**
 * Created by grenzfrequence on 11/03/17.
 */

public interface IResourceValues {

    String getString(@StringRes int resId);

    String getString(@StringRes int id, Object... formatArgs);

    boolean getBoolean(@BoolRes int id);

    int getInteger(@IntegerRes int id);

    Drawable getDrawable(@DrawableRes int id);

}
