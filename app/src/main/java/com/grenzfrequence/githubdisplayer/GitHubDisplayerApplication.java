package com.grenzfrequence.githubdisplayer;

import android.app.Application;
import android.content.Context;

import com.grenzfrequence.githubdisplayer.di.ApplicationComponent;
import com.grenzfrequence.githubdisplayer.di.ApplicationModule;
import com.grenzfrequence.githubdisplayer.di.DaggerApplicationComponent;
import com.grenzfrequence.githubdisplayer.webservice.NetModule;
import com.grenzfrequence.githubdisplayer.webservice.WebServiceConfig;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class GitHubDisplayerApplication extends Application {

    ApplicationComponent applicationComponent;

    public static GitHubDisplayerApplication get(Context context) {
        return (GitHubDisplayerApplication) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent =
                DaggerApplicationComponent
                        .builder()
                        .applicationModule(new ApplicationModule(this))
                        .netModule(new NetModule(WebServiceConfig.BASE_URL, WebServiceConfig.MAX_CACHE_SIZE))
                        .build();
    }
}
