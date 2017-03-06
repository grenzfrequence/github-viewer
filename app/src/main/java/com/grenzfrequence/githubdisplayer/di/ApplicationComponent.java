package com.grenzfrequence.githubdisplayer.di;

import com.grenzfrequence.githubdisplayer.utils.UrlReference;
import com.grenzfrequence.githubdisplayer.webservice.NetModule;
import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by grenzfrequence on 25/01/17.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetModule.class,
})
public interface ApplicationComponent {

    Retrofit getRetrofit();
    Moshi getMoshi();
    UrlReference getBaseUrlReference();

}
