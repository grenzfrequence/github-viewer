package com.grenzfrequence.githubdisplayer.base;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.BoolRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatDrawableManager;

import com.grenzfrequence.githubdisplayer.di.qualifiers.ActivityContextQualifier;

import javax.inject.Inject;

/**
 * Created by grenzfrequence on 11/03/17.
 */

public class ResourceValues implements IResourceValues {

    @ActivityContextQualifier private Context   context;
    private                           Resources res;

    @Inject
    public ResourceValues(@ActivityContextQualifier Context context) {
        this.context = context;
        this.res = context.getResources();
    }

    @Override
    public String getString(@StringRes int resId) {
        return res.getString(resId);
    }

    @Override
    public String getString(@StringRes int id, Object... formatArgs) {
        return res.getString(id, formatArgs);
    }

    @Override
    public boolean getBoolean(@BoolRes int id) {
        return res.getBoolean(id);
    }

    @Override
    public int getInteger(@IntegerRes int id) {
        return res.getInteger(id);
    }

    @Override
    public Drawable getDrawable(@DrawableRes int id) {
        return AppCompatDrawableManager.get().getDrawable(context, id);
    }
}
