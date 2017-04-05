package com.grenzfrequence.githubdisplayer.utils;

import android.net.UrlQuerySanitizer;

import static com.grenzfrequence.githubdisplayer.common.Constants.SPACE;

/**
 * Created by grenzfrequence on 30/03/17.
 */

public class GitHubUtils {

    private static final String LAST_PAGE_INDICATOR = "rel=\"last\"";
    private static final String PAGE_NR = "page";

    public static int getLastPageNr(String linkHeaderInfo) {
        int      pageNr    = 1;

        if (linkHeaderInfo == null) {
            return pageNr;
        }

        String[] linkValue = linkHeaderInfo.split(SPACE);
        for (int i = 0; i < linkValue.length; i += 2) {
            String relKey = linkValue[i + 1].replaceAll("[,]", "");
            if (relKey.equals(LAST_PAGE_INDICATOR)) {
                String relContent = linkValue[i].replaceAll("[<>;]", "");
                UrlQuerySanitizer sanitizer  = new UrlQuerySanitizer();
                sanitizer.setAllowUnregisteredParamaters(true);
                sanitizer.parseUrl(relContent);
                String sPageNr = sanitizer.getValue(PAGE_NR);
                pageNr = sPageNr == null ? 0 : Integer.parseInt(sPageNr);
                break;
            }
        }
        return pageNr;
    }
}
