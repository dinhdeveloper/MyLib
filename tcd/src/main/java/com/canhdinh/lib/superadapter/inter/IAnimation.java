package com.canhdinh.lib.superadapter.inter;

import androidx.recyclerview.widget.RecyclerView;

import com.canhdinh.lib.load_more.BaseAnimation;


public interface IAnimation {

    void enableLoadAnimation();

    void enableLoadAnimation(long duration, BaseAnimation animation);

    void cancelLoadAnimation();

    void setOnlyOnce(boolean onlyOnce);

    void addLoadAnimation(RecyclerView.ViewHolder holder);

}
