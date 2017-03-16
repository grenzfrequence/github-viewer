package com.grenzfrequence.githubdisplayer.binding_adapters;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by grenzfrequence on 12/03/17.
 */

public class ViewBindingAdapters {

    @BindingAdapter("android:visibility")
    public static void bindingVisibility(@NonNull View view, @Nullable Boolean visible) {
        int visibility = visible == null || !visible ? View.GONE : View.VISIBLE;
        view.setVisibility(visibility);
    }

}
