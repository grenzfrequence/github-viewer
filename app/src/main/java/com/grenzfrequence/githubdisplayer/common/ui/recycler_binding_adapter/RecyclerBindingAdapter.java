package com.grenzfrequence.githubdisplayer.common.ui.recycler_binding_adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.grenzfrequence.githubdisplayer.base.MvvmViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by grenzfrequence on 19/03/17.
 */

public class RecyclerBindingAdapter<ITEM, VIEWMODEL extends MvvmViewModel>
        extends RecyclerView.Adapter {

    private static final int TYPE_BINDING_VIEW_HOLDER      = 1;
    private static final int TYPE_PROGRESS_BAR_VIEW_HOLDER = 2;

    private List<ITEM> listItems = new ArrayList<>();

    private int itemLayout;
    private int progressBarLayout;
    private int viewModelId;
    private boolean showProgressBar = false;
    private OnCreatViewModel<VIEWMODEL> onCreateViewModel;

    private OnItemClickListener<ITEM> onItemClickListener = null;

    public RecyclerBindingAdapter(
            @LayoutRes int itemLayout,
            @LayoutRes int progressBarLoadMoreLayout,
            int viewModelId,
            OnCreatViewModel<VIEWMODEL> onCreatViewModel) {
        super();
        this.itemLayout = itemLayout;
        this.progressBarLayout = progressBarLoadMoreLayout;
        this.viewModelId = viewModelId;
        this.onCreateViewModel = onCreatViewModel;
        setHasStableIds(true);
    }

    public void setListItems(List<ITEM> listItems) {
        this.listItems = listItems;
        showProgressBar = false;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<ITEM> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int showOnLoadMore() {
        showProgressBar = true;
        notifyDataSetChanged();
        return 0;
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position != 0 && position == getItemCount() - 1) {
            return TYPE_PROGRESS_BAR_VIEW_HOLDER;
        }
        return TYPE_BINDING_VIEW_HOLDER;
    }

    @Override
    public long getItemId(int position) {
        if (position != 0 && position == getItemCount() - 1) {
            return -1;
        }
        return listItems.get(position).hashCode();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int  layoutId = viewType == TYPE_BINDING_VIEW_HOLDER ? itemLayout : progressBarLayout;
        View view     = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        if (viewType == TYPE_BINDING_VIEW_HOLDER) {
            return new BindingViewHolder<>(view, onCreateViewModel.create(), viewModelId);
        } else if (viewType == TYPE_PROGRESS_BAR_VIEW_HOLDER) {
            return new ProgressBarViewHolder(view);
        }
        throw new IllegalArgumentException("Invalid ViewType: " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BindingViewHolder) {
            // noinspection unchecked
            BindingViewHolder<VIEWMODEL> bindingViewHolder = (BindingViewHolder<VIEWMODEL>) holder;
            ITEM                         item              = listItems.get(position);

            if (onItemClickListener != null) {
                bindingViewHolder.getItemBinding().getRoot().setOnClickListener(view -> {
                    onItemClickListener.onItemClicked(item, position);
                });
            }
            // noinspection unchecked
            bindingViewHolder.getViewModel().setData(item);
            bindingViewHolder.executePendingBindings();

        } else if (holder instanceof ProgressBarViewHolder) {
            ProgressBar progressBarViewHolder = ((ProgressBarViewHolder) holder).getProgressBar();
            if(showProgressBar) {
                progressBarViewHolder.setIndeterminate(true);
                progressBarViewHolder.setVisibility(VISIBLE);
            }
            else {
                progressBarViewHolder.setVisibility(GONE);
            }
        }
    }

}
