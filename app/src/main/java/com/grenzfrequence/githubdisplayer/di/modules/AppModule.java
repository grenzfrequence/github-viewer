package com.grenzfrequence.githubdisplayer.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.grenzfrequence.githubdisplayer.GitHubApp;
import com.grenzfrequence.githubdisplayer.di.qualifiers.AppContextQualifier;
import com.grenzfrequence.githubdisplayer.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grenzfrequence on 03/03/17.
 */

@Module
public class AppModule {
    GitHubApp application;

    public AppModule(Application application) {
        this.application = (GitHubApp) application;
    }

    @Provides
    @AppScope
    Application provideApp() {
        return application;
    }

    @Provides
    @AppScope
    @AppContextQualifier
    Context provideAppContext() {
        return application;
    }

    @Provides
    @AppScope
    Resources provideResources() {
        return application.getResources();
    }
}
