package com.grenzfrequence.githubdisplayer.di.modules;

import android.app.Application;

import com.grenzfrequence.githubdisplayer.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grenzfrequence on 03/03/17.
 */

@Module
public class AppModule {
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationScope
    Application provideApplication() {
        return application;
    }
}
