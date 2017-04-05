package com.grenzfrequence.githubdisplayer.common.data;

import okhttp3.Headers;

/**
 * Created by grenzfrequence on 04/04/17.
 */

public interface DataPager {

    void reset();

    void updatePager(Headers url);

    boolean isMoreData(int requestedPageNr);

}
