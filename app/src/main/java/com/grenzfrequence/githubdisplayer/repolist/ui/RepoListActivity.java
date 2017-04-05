package com.grenzfrequence.githubdisplayer.repolist.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.base.BaseActivity;

public class RepoListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.repo_list_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
