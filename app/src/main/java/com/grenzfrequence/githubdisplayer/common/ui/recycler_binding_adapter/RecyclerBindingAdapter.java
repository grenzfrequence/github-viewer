package com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grenzfrequence.githubdisplayer.base.MvvmViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grenzfrequence on 19/03/17.
 */

public class RecyclerBindingAdapter<ITEM, VIEWMODEL extends MvvmViewModel>
        extends RecyclerView.Adapter<BindingViewHolder<VIEWMODEL>> {

    private List<ITEM> listItems = new ArrayList<>();

    private int                         itemLayout;
    private int                         viewModelId;
    private OnCreatViewModel<VIEWMODEL> onCreateViewModel;

    private OnItemClickListener<ITEM> onItemClickListener = null;

    public RecyclerBindingAdapter(
            @LayoutRes int itemLayout,
            int viewModelId,
            OnCreatViewModel<VIEWMODEL> onCreatViewModel) {
        super();
        this.itemLayout = itemLayout;
        this.viewModelId = viewModelId;
        this.onCreateViewModel = onCreatViewModel;
    }

    public void setListItems(List<ITEM> listItems) {
        this.listItems = listItems;
    }

    public void setOnItemClickListener(OnItemClickListener<ITEM> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public BindingViewHolder<VIEWMODEL> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(itemLayout, parent, false);
        return new BindingViewHolder<>(view, onCreateViewModel.create(), viewModelId);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<VIEWMODEL> holder, int position) {
        ITEM item = listItems.get(position);

        if (onItemClickListener != null) {
            holder.getItemBinding().getRoot().setOnClickListener(view -> {
                onItemClickListener.onItemClicked(item, position);
            });
        }

        // noinspection unchecked
        holder.getViewModel().setData(item);
        holder.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

}
