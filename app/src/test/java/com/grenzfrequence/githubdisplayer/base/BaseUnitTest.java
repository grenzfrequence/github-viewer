package com.grenzfrequence.githubdisplayer.base;

import android.support.v7.app.AppCompatActivity;

import com.grenzfrequence.githubdisplayer.GitHubApp;
import com.grenzfrequence.githubdisplayer.di.components.ActivityComponent;
import com.grenzfrequence.githubdisplayer.di.components.AppComponent;
import com.grenzfrequence.githubdisplayer.di.components.FragmentComponent;
import com.grenzfrequence.githubdisplayer.utils.ComponentManager;
import com.grenzfrequence.githubdisplayer.utils.UrlReference;

import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

/**
 * Created by grenzfrequence on 15/03/17.
 */

public class BaseUnitTest {

    @Mock
    AppCompatActivity mockedActivity;

    private GitHubApp getApp() {
        return (GitHubApp) RuntimeEnvironment.application;
    }

    protected AppComponent getAppComponent() {
        return getApp().getAppComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return ComponentManager.getActivityComponent(mockedActivity);
    }

    protected FragmentComponent getFragmentComponent() {
        return ComponentManager
                .getFragmentComponent(getActivityComponent());
    }

    protected UrlReference getBaseUrlReference() {
        return getAppComponent().baseUrlReference();
    }

}
