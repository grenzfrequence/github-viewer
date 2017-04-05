package com.grenzfrequence.githubdisplayer.common.ui;

import java.util.List;

/**
 * Created by grenzfrequence on 26/03/17.
 */

public interface IRefreshableRecyclerView<ITEM> extends IRefreshableView {

    void setListItems(List<ITEM> listItems, boolean resetData);

}
