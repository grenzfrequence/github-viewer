package com.grenzfrequence.githubdisplayer.webservice;

import okhttp3.logging.HttpLoggingInterceptor.Level;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;

/**
 * Created by grenzfrequence on 21/01/17.
 */

public class WebServiceConfig {

    public static final String BASE_URL       = "http://api.github.com/";
    public static final int    MAX_CACHE_SIZE = 10 * 1024 * 1024;
    public static final Level  LOGGING_LEVEL  = HEADERS;
}
