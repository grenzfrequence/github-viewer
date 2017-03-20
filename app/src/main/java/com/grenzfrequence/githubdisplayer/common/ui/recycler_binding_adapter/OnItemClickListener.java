package com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter;

/**
 * Created by grenzfrequence on 19/03/17.
 */

public interface OnItemClickListener<ITEM> {
    void onItemClicked(ITEM item, int position);
}
