package com.grenzfrequence.githubdisplayer;

import android.content.Context;

import com.grenzfrequence.githubdisplayer.di.components.AppComponent;
import com.grenzfrequence.githubdisplayer.di.components.DaggerAppComponent;
import com.grenzfrequence.githubdisplayer.di.modules.AppModule;
import com.grenzfrequence.githubdisplayer.di.modules.NetModule;
import com.grenzfrequence.githubdisplayer.webservice.WebServiceConfig;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class Application extends android.app.Application {

    AppComponent applicationComponent;

    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent =
                DaggerAppComponent
                        .builder()
                        .appModule(new AppModule(this))
                        .netModule(new NetModule(WebServiceConfig.BASE_URL, WebServiceConfig.MAX_CACHE_SIZE))
                        .build();
    }
}
