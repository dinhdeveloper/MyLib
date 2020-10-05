package com.canhdinh.mylib.api;

import com.canhdinh.mylib.model.BaseResponseModel;
import com.canhdinh.mylib.model.BookingResultModel;
import com.canhdinh.mylib.model.Product;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("product/list")
    Call<List<Product>> getAllProduct();

    @GET("/product/search={search}")
    Call<List<Product>> searchProduct(@Path("search") String search);

    @Headers("Authorization:Basic YWRtaW46cXRjdGVrQDEyMwx==")
    @Multipart
    @POST("/api")
    Call<RequestBody> updateImage(
            @Body RequestBody model
    );
}
