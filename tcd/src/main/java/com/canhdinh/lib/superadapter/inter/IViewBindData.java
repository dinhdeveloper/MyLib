package com.canhdinh.lib.superadapter.inter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public interface IViewBindData<T, VH> {

    /**
     * @param parent      Target container(ListView, GridView, RecyclerView,Spinner, etc.).
     * @param viewType    Choose the layout resource according to view type.
     * @return Created view holder.
     */
    VH onCreate(@Nullable View convertView, ViewGroup parent, int viewType);

    /**
     * Method for binding data to view.
     *
     * @param holder         ViewHolder
     * @param layoutPosition position
     * @param item           data
     */
    void onBind(VH holder, int viewType, int layoutPosition, T item);

}
