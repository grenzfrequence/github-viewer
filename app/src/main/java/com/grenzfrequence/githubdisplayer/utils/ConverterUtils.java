package com.grenzfrequence.githubdisplayer.utils;

import org.joda.time.DateTime;

/**
 * Created by grenzfrequence on 16/03/17.
 */

public class ConverterUtils {

    public static DateTime stringToDate(String utcString) {
        return new DateTime(utcString);
    }

    public static String dateToString(DateTime date) {
        return date.toString("dd.MM.yyyy");
    }
}
