package com.grenzfrequence.githubdisplayer.binding_adapters;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by grenzfrequence on 12/03/17.
 */

public class TextViewAdapters {

    @BindingAdapter("android:text")
    public static void setTextResource(TextView textView, int stringResId) {
        Resources res = textView.getResources();
        textView.setText(res.getString(stringResId));
    }
}
