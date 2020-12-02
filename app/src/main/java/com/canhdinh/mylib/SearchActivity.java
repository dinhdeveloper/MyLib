package com.canhdinh.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.canhdinh.lib.helper.MyToast;
import com.canhdinh.lib.rippleview.RippleView;
import com.canhdinh.lib.searchdialog.SimpleSearchDialogCompat;
import com.canhdinh.lib.searchdialog.core.BaseFilter;
import com.canhdinh.lib.searchdialog.core.BaseSearchDialogCompat;
import com.canhdinh.lib.searchdialog.core.FilterResultListener;
import com.canhdinh.lib.searchdialog.core.SearchResultListener;
import com.canhdinh.lib.searchdialog.core.Searchable;
import com.canhdinh.lib.widget.WaveSwipeRefreshLayout;
import com.canhdinh.mylib.api.APIService;
import com.canhdinh.mylib.api.APIUntil;
import com.canhdinh.mylib.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements WaveSwipeRefreshLayout.OnRefreshListener {

    APIService apiService;
    private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        apiService = APIUntil.getServer();
        provideSimpleDialogWithApiCalls();

        final RippleView rippleView = (RippleView) findViewById(R.id.rect);
        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        mWaveSwipeRefreshLayout.setOnRefreshListener(this);
        mWaveSwipeRefreshLayout.setWaveColor(Color.argb(100, 255, 0, 0));

        rippleView.setOnRippleCompleteListener(rippleView1 -> {
            getDataApi();
        });
    }

    private void getDataApi() {
        SimpleSearchDialogCompat<Product> searchDialog =
                new SimpleSearchDialogCompat<Product>(SearchActivity.this, "Tìm kiếm",
                        "Nhập tên sản phẩm", null, new ArrayList<>(),
                        (dialog, item, position) -> {
                            MyToast.show(SearchActivity.this, item.getTitle());
                            dialog.dismiss();
                        });

        BaseFilter baseFilter = new BaseFilter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                doBeforeFiltering();
                FilterResults results = new FilterResults();
                results.values = new ArrayList<Product>();
                results.count = 0;
                apiService.searchProduct(constraint.toString()).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.body() != null) {
                            results.values = response.body();
                            results.count = response.body().size();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Log.e("onFailure", t.getMessage());
                    }
                });
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                if (filterResults != null) {
                    ArrayList<Product> filter = (ArrayList<Product>) filterResults.values;
                    if (filter != null) {
                        searchDialog.getFilterResultListener().onFilter(filter);
                    }
                    doAfterFiltering();
                }
            }
        };
        searchDialog.setFilter(baseFilter);
        searchDialog.show();
    }

    void provideSimpleDialogWithApiCalls() {
        final SimpleSearchDialogCompat<Product> searchDialog =
                new SimpleSearchDialogCompat(SearchActivity.this, "Tìm kiếm...",
                        "Nhập tên sản phẩm?", null, new ArrayList(),
                        new SearchResultListener<Searchable>() {
                            @Override
                            public void onSelected(
                                    BaseSearchDialogCompat dialog,
                                    Searchable item, int position
                            ) {
                                Toast.makeText(SearchActivity.this, item.getTitle(),
                                        Toast.LENGTH_SHORT
                                ).show();
                                dialog.dismiss();
                            }
                        }
                );
        BaseFilter apiFilter = new BaseFilter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                doBeforeFiltering();
                FilterResults results = new FilterResults();
                results.values = new ArrayList<Product>();
                results.count = 0;
                try {
                    ArrayList<Product> users = (ArrayList<Product>) apiService.searchProduct(charSequence.toString()).execute().body();
                    if (users != null) {
                        results.values = users;
                        results.count = users.size();
                    }
                    return results;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults != null) {
                    ArrayList<Product> filtered = (ArrayList<Product>) filterResults.values;
                    if (filtered != null) {
                        searchDialog.getFilterResultListener().onFilter(filtered);
                    }
                    doAfterFiltering();
                }
            }
        };
        searchDialog.setFilter(apiFilter);
        searchDialog.show();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWaveSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }
}