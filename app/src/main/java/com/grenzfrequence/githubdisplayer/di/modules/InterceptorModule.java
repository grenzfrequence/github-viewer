package com.grenzfrequence.githubdisplayer.di.modules;

import android.support.annotation.NonNull;

import com.grenzfrequence.githubdisplayer.di.scopes.AppScope;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

import static java.util.Collections.singletonList;

/**
 * Created by grenzfrequence on 10/03/17.
 */

@Module
public class InterceptorModule {

    Level loggingLevel;

    public InterceptorModule(Level loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    @Provides
    @AppScope
    @NonNull
    List<Interceptor> provideHttpLoggingInterceptor() {
        return singletonList(new HttpLoggingInterceptor().setLevel(loggingLevel));
    }

}
