package com.grenzfrequence.githubdisplayer.binding_adapters;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.grenzfrequence.githubdisplayer.utils.ObjectUtils;

/**
 * Created by grenzfrequence on 12/03/17.
 */

public class ImageViewAdapters {

    @BindingAdapter("srcCompat")
    public static void setImageResource(ImageView imageView, int resourceId) {
        imageView.setImageResource(resourceId);
    }

    @BindingAdapter({"srcCompat", "app:placeholder"})
    public static void loadImageFile(
            ImageView imageView, String imageUrl, Drawable placeholder) {

        if (ObjectUtils.isNullOrEmpty(imageUrl)) {
            imageView.setImageDrawable(placeholder);
            return;
        }
        Glide.with(imageView.getContext())
             .load(imageUrl)
             .placeholder(placeholder)
             .centerCrop()
             .crossFade()
             .into(imageView);
    }

}
