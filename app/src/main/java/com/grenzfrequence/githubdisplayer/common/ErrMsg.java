package com.grenzfrequence.githubdisplayer.common;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ErrMsg {

    @NonNull
    public static ErrMsg create(@StringRes int errorMessageId, @DrawableRes int errorIconId) {
        return new AutoValue_ErrMsg(errorMessageId, errorIconId);
    }

    @StringRes
    public abstract int getErrorMessageId();

    @DrawableRes
    public abstract int getErrorIconId();
}