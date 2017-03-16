package com.grenzfrequence.githubdisplayer.di.modules;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.grenzfrequence.githubdisplayer.di.qualifiers.ActivityContextQualifier;
import com.grenzfrequence.githubdisplayer.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by grenzfrequence on 09/03/17.
 */

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    @ActivityContextQualifier
    Context provideActivityContext() {
        return activity;
    }

}
