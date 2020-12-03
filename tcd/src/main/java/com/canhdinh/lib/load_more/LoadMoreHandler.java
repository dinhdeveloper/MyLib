package com.canhdinh.lib.load_more;

import android.view.View;

public interface LoadMoreHandler {

    /**
     * @param contentView
     * @param loadMoreView
     * @param onClickLoadMoreListener
     * @return init ILoadMoreView
     */
    public boolean handleSetAdapter(View contentView, ILoadMoreViewFactory.ILoadMoreView loadMoreView, View.OnClickListener
            onClickLoadMoreListener);

    public void setOnScrollBottomListener(View contentView, OnScrollBottomListener onScrollBottomListener);

    void removeFooter();
    void addFooter();
}
