package com.grenzfrequence.githubdisplayer.repolist.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grenzfrequence.githubdisplayer.R;
import com.grenzfrequence.githubdisplayer.repolist.data.RepoListItem;

import java.util.List;

/**
 * Created by grenzfrequence on 04/03/17.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListItemHolder> {

    private List<RepoListItem> repoListItems;

    public RepoListAdapter(List<RepoListItem> repoListItems) {
        this.repoListItems = repoListItems;
    }

    @Override
    public RepoListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
             .inflate(R.layout.repo_list_item, null, false);
        return new RepoListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoListItemHolder holder, int position) {
        holder.setData(repoListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return repoListItems.size();
    }
}
