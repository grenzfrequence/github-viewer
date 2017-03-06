package com.grenzfrequence.githubdisplayer.repolist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListItem;
import com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview.RepoListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by grenzfrequence on 01/03/17.
 */

public class RepoListFragment extends Fragment {

    @BindView(R.id.tv_user_name) TextView     tvUserName;
    @BindView(R.id.rv_repo_list) RecyclerView rvRepoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repo_list_fragment, container, false);
        ButterKnife.bind(this, view);
        rvRepoList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //
        // get here saved instances when != null
        //
        update();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //
        // save here instances for configuration changes
        //
    }

    void update() {
        List<RepoListItem> repoListItems = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            RepoListItem repoListItem = RepoListItem.builder().repoName("Test " + i).build();
            repoListItems.add(repoListItem);
        }
        rvRepoList.setAdapter(new RepoListAdapter(repoListItems));
    }

}
