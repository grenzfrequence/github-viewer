package com.grenzfrequence.githubdisplayer.base;

/**
 * Created by grenzfrequence on 09/03/17.
 */

public abstract class BaseRecyclerViewModel<VIEW extends MvvmView, ITEM>
        extends BaseViewModel<VIEW, ITEM>
        implements ILoaderViewModel<VIEW, ITEM> {
}
