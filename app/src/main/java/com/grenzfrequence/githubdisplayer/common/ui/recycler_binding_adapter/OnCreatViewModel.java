package com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter;

import com.grenzfrequence.githubdisplayer.base.MvvmViewModel;

/**
 * Created by grenzfrequence on 20/03/17.
 */

public interface OnCreatViewModel<VIEWMODEL extends MvvmViewModel> {
    VIEWMODEL create();
}
