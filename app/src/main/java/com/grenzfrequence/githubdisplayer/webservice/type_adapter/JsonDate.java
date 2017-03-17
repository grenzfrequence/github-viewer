package com.grenzfrequence.githubdisplayer.webservice.type_adapter;

import com.squareup.moshi.JsonQualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by grenzfrequence on 16/03/17.
 */

@Retention(RetentionPolicy.RUNTIME)
@JsonQualifier
public @interface JsonDate {
}
