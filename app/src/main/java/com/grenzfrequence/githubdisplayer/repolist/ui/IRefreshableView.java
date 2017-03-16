package com.grenzfrequence.githubdisplayer.repolist.ui;

import com.grenzfrequence.githubdisplayer.base.MvvmView;

/**
 * Created by grenzfrequence on 08/03/17.
 */

public interface IRefreshableView extends MvvmView {

    void showRefresh(boolean show);

    void onRefreshed(Boolean success);

}
