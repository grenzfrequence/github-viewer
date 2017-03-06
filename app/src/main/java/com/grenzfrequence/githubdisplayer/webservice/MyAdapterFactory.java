package com.grenzfrequence.githubdisplayer.webservice;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Created by grenzfrequence on 02/03/17.
 */

@MoshiAdapterFactory
public abstract class MyAdapterFactory implements JsonAdapter.Factory {

    // Static factory method to access the package
    // private generated implementation
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_MyAdapterFactory();
    }

}