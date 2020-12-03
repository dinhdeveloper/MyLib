package com.canhdinh.lib.load_more;

import android.view.View;

public interface ILoadMoreViewFactory {

    public ILoadMoreView madeLoadMoreView();

    public interface ILoadMoreView {

        public void init(FootViewAdder footViewHolder, View.OnClickListener onClickLoadMoreListener);

        public void showNormal();

        public void showNomore();

        public void showLoading();

        public void showFail(Exception e);

        public void setFooterVisibility(boolean isVisible);

    }

    public static interface FootViewAdder {

        public View addFootView(View view);

        public View addFootView(int layoutId);

    }

}
