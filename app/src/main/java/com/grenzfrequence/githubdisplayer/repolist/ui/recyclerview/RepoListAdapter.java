package com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by grenzfrequence on 04/03/17.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListItemViewHolder> {

    private List<RepoModel> repoListItems = new ArrayList<>();

    @Inject
    public RepoListAdapter() {
        super();
    }

    public void setRepoListItems(List<RepoModel> repoListItems) {
        this.repoListItems = repoListItems;
    }

    @Override
    public RepoListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.repo_list_item, parent, false);
        return new RepoListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoListItemViewHolder holder, int position) {
        if (repoListItems == null) {
            return;
        }
        holder.getViewModel().setRepoListItem(repoListItems.get(position));
        holder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return repoListItems == null ? 0 : repoListItems.size();
    }
}
