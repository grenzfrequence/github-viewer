package com.grenzfrequence.githubdisplayer.di.modules;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import com.grenzfrequence.githubdisplayer.di.scopes.AppScope;
import com.grenzfrequence.githubdisplayer.utils.UrlReference;
import com.grenzfrequence.githubdisplayer.webservice.MyAdapterFactory;
import com.squareup.moshi.Moshi;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

    @Provides
    @AppScope
    @NonNull
    public UrlReference provideUrlReference() {
        return baseUrlReference;
    }

    @Provides
    @AppScope
    @NonNull
    Retrofit provideRetrofit(Moshi moshi, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrlReference.url())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .validateEagerly(BuildConfig.DEBUG)
                .build();
    }

    @Provides
    @AppScope
    @NonNull
    Moshi provideMoshi() {
        return new Moshi
                .Builder()
                .add(MyAdapterFactory.create())
                .build();
    }

    @Provides
    @AppScope
    @NonNull
    OkHttpClient provideClient(Cache cache, List<Interceptor> interceptors) {

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            httpBuilder.addInterceptor(interceptor);
        }

        return httpBuilder
                .cache(cache)
                .build();
    }

    @Provides
    @AppScope
    @NonNull
    Cache provideCache(Application application) {
        return new Cache(application.getCacheDir(), maxCacheSize);
    }

}
