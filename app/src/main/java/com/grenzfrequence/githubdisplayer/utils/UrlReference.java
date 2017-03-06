package com.grenzfrequence.githubdisplayer.utils;


import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicReference;

import okhttp3.HttpUrl;

/**
 * Created by grenzfrequence on 24/02/17.
 */

public class UrlReference {

    private AtomicReference<HttpUrl> urlReference;

    public UrlReference(String url) {
        this.urlReference = new AtomicReference<>(HttpUrl.parse(url));
    }

    public void setUrl(@NonNull HttpUrl url) {
        urlReference.set(url);
    }

    public HttpUrl url() {
        return urlReference.get();
    }
}
