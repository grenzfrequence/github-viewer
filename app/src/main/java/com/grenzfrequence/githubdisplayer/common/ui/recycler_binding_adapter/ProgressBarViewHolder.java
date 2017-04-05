package com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.grenzfrequence.githubdisplayer.R;

/**
 * Created by grenzfrequence on 26/03/17.
 */

public class ProgressBarViewHolder extends RecyclerView.ViewHolder {

    private ProgressBar progressBar;

    public ProgressBarViewHolder(View view) {
        super(view);
        this.progressBar = (ProgressBar) view.findViewById(R.id.recycler_binding_progress_bar);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
