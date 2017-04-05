package com.grenzfrequence.githubdisplayer.base;

import java.util.List;

/**
 * Created by grenzfrequence on 07/03/17.
 */

public interface IRecyclerViewModel<VIEW extends MvvmView, ITEM> extends ILoaderViewModel<VIEW, ITEM> {
    void setListItems(List<ITEM> listItems);
    void notifyDataSetChanged();
}
