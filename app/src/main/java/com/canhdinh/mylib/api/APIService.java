package com.canhdinh.mylib.api;

import com.canhdinh.mylib.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("product/list")
    Call<List<Product>> getAllProduct();

    @GET("/product/search={search}")
    Call<List<Product>> searchProduct(@Path("search") String search);

}
