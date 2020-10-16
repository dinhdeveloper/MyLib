package com.canhdinh.mylib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.canhdinh.lib.recyclerview.ProgressStyle;
import com.canhdinh.lib.recyclerview.XRecyclerView;
import com.canhdinh.mylib.adapter.ProductAdapter;
import com.canhdinh.mylib.api.APIService;
import com.canhdinh.mylib.api.APIUntil;
import com.canhdinh.mylib.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadMoreActivity extends AppCompatActivity {

    XRecyclerView mRecyclerView;
    APIService apiService;
    ArrayList<Product> products = new ArrayList<>();
    ProductAdapter adapter;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);
        apiService = APIUntil.getServer();
        mRecyclerView = this.findViewById(R.id.recyclerview);
        getData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);

        final int itemLimit = 5;
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        products.clear();
                        for(int i = 0; i < itemLimit ;i++){
                           getData();
                        }
                        adapter.notifyDataSetChanged();
                        if(mRecyclerView != null)
                            mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            for(int i = 0; i < itemLimit ;i++){
                                getData();
                            }
                            if(mRecyclerView != null) {
                                mRecyclerView.loadMoreComplete();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for(int i = 0; i < itemLimit ;i++){
                                getData();
                            }
                            if(mRecyclerView != null) {
                                mRecyclerView.setNoMore(true);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }, 1000);
                }
                times ++;
            }
        });

        adapter = new ProductAdapter(LoadMoreActivity.this,products);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.refresh();

    }

    private void getData() {
        apiService.getAllProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = (ArrayList<Product>) response.body();
                adapter = new ProductAdapter(LoadMoreActivity.this,products);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
    }
}