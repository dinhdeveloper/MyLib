package com.canhdinh.lib.load_more;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.canhdinh.lib.R;

public class DefaultLoadMoreViewFooter implements ILoadMoreViewFactory {

    @Override
    public ILoadMoreView madeLoadMoreView() {
        return new LoadMoreHelper();
    }

    private class LoadMoreHelper implements ILoadMoreView {

        protected View footerView;
        protected TextView footerTv;
        protected ProgressBar footerBar;

        protected View.OnClickListener onClickRefreshListener;

        @Override
        public void init(FootViewAdder footViewHolder, View.OnClickListener onClickRefreshListener) {
            footerView = footViewHolder.addFootView(R.layout.cptr_loadmore_default_footer);
            footerTv = (TextView) footerView.findViewById(R.id.loadmore_default_footer_tv);
            footerBar = (ProgressBar) footerView.findViewById(R.id.loadmore_default_footer_progressbar);
            this.onClickRefreshListener = onClickRefreshListener;
            showNormal();
        }

        @Override
        public void showNormal() {
            footerTv.setText(R.string.cube_ptr_release_to_refresh);
            footerBar.setVisibility(View.GONE);
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showLoading() {
            footerTv.setText(R.string.cube_ptr_refreshing);
            footerBar.setVisibility(View.VISIBLE);
            footerView.setOnClickListener(null);
        }

        @Override
        public void showFail(Exception exception) {
            footerTv.setText(R.string.cube_ptr_showFail);
            footerBar.setVisibility(View.GONE);
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showNomore() {
            footerTv.setText(R.string.cube_ptr_nomore);
            footerBar.setVisibility(View.GONE);
            footerView.setOnClickListener(null);
        }

        @Override
        public void setFooterVisibility(boolean isVisible) {
            footerView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

}