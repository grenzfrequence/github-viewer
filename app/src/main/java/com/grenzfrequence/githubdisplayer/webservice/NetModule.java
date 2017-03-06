package com.grenzfrequence.githubdisplayer.webservice;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import com.grenzfrequence.githubdisplayer.utils.UrlReference;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by grenzfrequence on 21/01/17.
 */

@Module
public class NetModule {

    private UrlReference baseUrlReference;
    private int          maxCacheSize;

    public NetModule(String baseUrl, int maxCacheSize) {
        this.baseUrlReference = new UrlReference(baseUrl);
        this.maxCacheSize = maxCacheSize;
    }

    @NonNull
    @Singleton
    @Provides
    public UrlReference provideUrlReference() {
        return baseUrlReference;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Moshi moshi, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrlReference.url())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .validateEagerly(BuildConfig.DEBUG)
                .build();
    }

    @Singleton
    @Provides
    Moshi provideMoshi() {
        return new Moshi
                .Builder()
                .add(MyAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(Cache cache, HttpLoggingInterceptor httpLoggingInterceptor) {

        return new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), maxCacheSize);
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return (new HttpLoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

}
