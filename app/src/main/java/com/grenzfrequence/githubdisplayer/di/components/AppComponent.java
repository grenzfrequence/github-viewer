package com.grenzfrequence.githubdisplayer.di.components;

import com.grenzfrequence.githubdisplayer.di.modules.AppModule;
import com.grenzfrequence.githubdisplayer.di.modules.NetModule;
import com.grenzfrequence.githubdisplayer.di.scopes.ApplicationScope;
import com.grenzfrequence.githubdisplayer.utils.UrlReference;
import com.squareup.moshi.Moshi;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 25/01/17.
 */


@Component(modules = {AppModule.class, NetModule.class})
@ApplicationScope
public interface AppComponent {

    Retrofit getRetrofit();

    Moshi getMoshi();

    UrlReference getBaseUrlReference();

}
