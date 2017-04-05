package com.grenzfrequence.githubdisplayer.common.data;

import com.grenzfrequence.githubdisplayer.utils.GitHubUtils;

import okhttp3.Headers;

/**
 * Created by grenzfrequence on 04/04/17.
 */

public class GitHubPager implements DataPager {

    private static final String QUERY_LINK = "Link";

    private int maxPageNumber = 1;

    @Override
    public void reset() {
        maxPageNumber = 1;
    }

    @Override
    public void updatePager(Headers headers) {
        maxPageNumber = GitHubUtils.getLastPageNr(headers.get(QUERY_LINK));
    }

    @Override
    public boolean isMoreData(int requestedPageNr) {
        return requestedPageNr <= maxPageNumber;
    }
}
