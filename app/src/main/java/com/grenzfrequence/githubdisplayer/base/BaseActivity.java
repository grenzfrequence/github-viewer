package com.grenzfrequence.githubdisplayer.base;

import android.support.v7.app.AppCompatActivity;

import com.grenzfrequence.githubdisplayer.di.components.ActivityComponent;
import com.grenzfrequence.githubdisplayer.utils.ComponentManager;

/**
 * Created by grenzfrequence on 09/03/17.
 */

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent = null;

    protected final ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ComponentManager.getActivityComponent(this);
        }
        return activityComponent;
    }

}
