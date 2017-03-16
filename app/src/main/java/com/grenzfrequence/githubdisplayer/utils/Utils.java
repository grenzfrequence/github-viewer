package com.grenzfrequence.githubdisplayer.utils;

import java.util.List;

/**
 * Created by grenzfrequence on 12/03/17.
 */

public class Utils {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static String getEmptyOrValue(String string) {
        return isNullOrEmpty(string) ? "" : string;
    }
}
