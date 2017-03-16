package com.grenzfrequence.githubdisplayer.utils;

import android.support.v7.app.AppCompatActivity;

import com.grenzfrequence.githubdisplayer.GitHubApp;
import com.grenzfrequence.githubdisplayer.di.components.ActivityComponent;
import com.grenzfrequence.githubdisplayer.di.components.DaggerActivityComponent;
import com.grenzfrequence.githubdisplayer.di.components.DaggerFragmentComponent;
import com.grenzfrequence.githubdisplayer.di.components.FragmentComponent;
import com.grenzfrequence.githubdisplayer.di.modules.ActivityModule;
import com.grenzfrequence.githubdisplayer.di.modules.FragmentModule;

/**
 * Created by grenzfrequence on 15/03/17.
 */

public class ComponentManager {

    public static ActivityComponent getActivityComponent(AppCompatActivity activity) {
        return DaggerActivityComponent
                .builder()
                .appComponent(GitHubApp.getAppComponent())
                .activityModule(new ActivityModule(activity))
                .build();
    }

    public static FragmentComponent getFragmentComponent(ActivityComponent activityComponent) {
        return DaggerFragmentComponent
                .builder()
                .activityComponent(activityComponent)
                .fragmentModule(new FragmentModule())
                .build();
    }


}
