package com.grenzfrequence.githubdisplayer.binding_adapters;

import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.grenzfrequence.githubdisplayer.utils.ConverterUtils;

import org.joda.time.DateTime;

/**
 * Created by grenzfrequence on 12/03/17.
 */

public class TextViewAdapters {

    @BindingAdapter("android:text")
    public static void setTextResource(TextView textView, int stringResId) {
        Resources res = textView.getResources();
        textView.setText(res.getString(stringResId));
    }

    @BindingAdapter("android:text")
    public static void convertDateToString(TextView textView, DateTime date) {
        if(date == null) {
            return;
        }
        String dateString = ConverterUtils.dateToString(date);
        textView.setText(dateString);
    }
}
