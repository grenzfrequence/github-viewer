package com.grenzfrequence.githubdisplayer.webservice.type_adapter;

import com.grenzfrequence.githubdisplayer.utils.ConverterUtils;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import org.joda.time.DateTime;

/**
 * Created by grenzfrequence on 16/03/17.
 */

public class DateAdapter {

    @FromJson
    @JsonDate
    public DateTime fromJson(String utcString) {
        return ConverterUtils.stringToDate(utcString);
    }

    @ToJson
    public String toJson(@JsonDate DateTime date) {
        return ConverterUtils.dateToString(date);
    }

}
