package com.grenzfrequence.githubdisplayer.binding_adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.grenzfrequence.githubdisplayer.R;

/**
 * Created by grenzfrequence on 12/03/17.
 */

public class ImageViewAdapters {

    @BindingAdapter("srcCompat")
    public static void setImageResource(ImageView imageView, int resourceId) {
        imageView.setImageResource(resourceId);
    }

    @BindingAdapter("srcCompat")
    public static void loadImageFile(ImageView imageView, String imageUrl) {
        if (imageUrl == null) {
            return;
        }
        Glide.with(imageView.getContext())
             .load(imageUrl)
             .centerCrop()
             .placeholder(R.drawable.ic_face_black_24dp)
             .crossFade()
             .into(imageView);
    }

}