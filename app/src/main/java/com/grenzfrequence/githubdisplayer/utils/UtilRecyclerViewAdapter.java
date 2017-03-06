package com.grenzfrequence.githubdisplayer.utils;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by grenzfrequence on 02/03/17.
 */

public class UtilRecyclerViewAdapter {

    public static <T extends ViewDataBinding> T createBinding(Context context, ViewGroup parent, int layoutId) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return DataBindingUtil.inflate(layoutInflater, layoutId, parent, false);
    }
}
