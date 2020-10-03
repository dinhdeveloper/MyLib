package com.canhdinh.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.canhdinh.mylib.api.APIService;
import com.canhdinh.mylib.api.APIUntil;
import com.canhdinh.mylib.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private APIService apiService = APIUntil.getServer();

    public MutableLiveData<List<Product>> getDataProduct() {
        MutableLiveData<List<Product>> data = new MutableLiveData<List<Product>>();

        apiService.getAllProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });

        return data;
    }
}
