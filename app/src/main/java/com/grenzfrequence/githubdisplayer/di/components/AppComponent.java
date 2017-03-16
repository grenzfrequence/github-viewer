package com.grenzfrequence.githubdisplayer.di.components;

import android.app.Application;
import android.content.res.Resources;

import com.grenzfrequence.githubdisplayer.di.modules.AppModule;
import com.grenzfrequence.githubdisplayer.di.modules.InterceptorModule;
import com.grenzfrequence.githubdisplayer.di.modules.NetModule;
import com.grenzfrequence.githubdisplayer.di.scopes.AppScope;
import com.grenzfrequence.githubdisplayer.utils.UrlReference;
import com.squareup.moshi.Moshi;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 25/01/17.
 */


@Component(modules = {AppModule.class, NetModule.class, InterceptorModule.class})
@AppScope
public interface AppComponent {

    Application application();

    Resources resources();

    Retrofit retrofit();

    Moshi moshi();

    UrlReference baseUrlReference();

}
