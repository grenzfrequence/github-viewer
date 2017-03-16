package com.grenzfrequence.githubdisplayer;

import com.grenzfrequence.githubdisplayer.di.components.AppComponent;
import com.grenzfrequence.githubdisplayer.di.components.DaggerAppComponent;
import com.grenzfrequence.githubdisplayer.di.modules.AppModule;
import com.grenzfrequence.githubdisplayer.di.modules.InterceptorModule;
import com.grenzfrequence.githubdisplayer.di.modules.NetModule;
import com.grenzfrequence.githubdisplayer.webservice.WebServiceConfig;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class GitHubApp extends android.app.Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent =
                DaggerAppComponent
                        .builder()
                        .appModule(new AppModule(this))
                        .netModule(new NetModule(WebServiceConfig.BASE_URL, WebServiceConfig.MAX_CACHE_SIZE))
                        .interceptorModule(new InterceptorModule(WebServiceConfig.LOGGING_LEVEL))
                        .build();

    }
}
