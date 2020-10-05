package com.canhdinh.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.canhdinh.api.ApiRequest;
import com.canhdinh.api.ErrorApiResponse;
import com.canhdinh.helper.AppProvider;
import com.canhdinh.lib.helper.MyLog;
import com.canhdinh.lib.roundview.RoundTextView;
import com.canhdinh.lib.selectimage.BSImagePicker;
import com.canhdinh.mylib.api.APIService;
import com.canhdinh.mylib.api.APIUntil;
import com.canhdinh.mylib.api.RequestUpdateResultPayment;
import com.canhdinh.mylib.model.BaseResponseModel;
import com.canhdinh.mylib.model.BookingResultModel;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectImageActivity extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate, BSImagePicker.OnSelectImageCancelledListener {
    private RoundTextView btnUpdateResult, btnSubmit;
    private ImageView imvImageResult;
    String payment_image;
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        apiService = APIUntil.getServer();

        btnUpdateResult = findViewById(R.id.btnUpdateResult);
        btnSubmit = findViewById(R.id.btnSubmit);
        imvImageResult = findViewById(R.id.imvImageResult);

        btnUpdateResult.setOnClickListener(view -> {
            BSImagePicker pickerDialog = new BSImagePicker.Builder("com.canhdinh.mylib.fileprovider")
                    .build();
            pickerDialog.show(getSupportFragmentManager(), "picker");
        });

        btnSubmit.setOnClickListener(view -> {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            if (!TextUtils.isEmpty(payment_image)) {
                File fileAvatar = new File(payment_image);
                if (fileAvatar.exists()) {
                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), fileAvatar);
                    builder.addFormDataPart("payment_image", fileAvatar.getName(), fileBody);
                }
            }
            if (!TextUtils.isEmpty("74")) {
                builder.addFormDataPart("id_booking", "74");
            }
            builder.addFormDataPart("type_manager", "update_result_payment");
            builder.addFormDataPart("detect", "booking_manager")
                    .setType(MultipartBody.FORM);

            RequestBody requestBody = builder.build();

            BookingResultModel model = new BookingResultModel();
            model.setId("87");
            model.setImage_upload(payment_image);

            apiService.updateImage(requestBody).enqueue(new Callback<RequestBody>() {
                @Override
                public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(SelectImageActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RequestBody> call, Throwable t) {
                    MyLog.LogError("onFailure",t.getMessage());
                }
            });
        });
    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        payment_image = uri.getPath();
        Glide.with(SelectImageActivity.this).load(uri).into(imvImageResult);
    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {
    }

    @Override
    public void loadImage(Uri imageUri, ImageView ivImage) {
        Glide.with(SelectImageActivity.this).load(imageUri).into(ivImage);
    }

    @Override
    public void onCancelled(boolean isMultiSelecting, String tag) {
        Toast.makeText(this, "Selection is cancelled, Multi-selection is " + isMultiSelecting, Toast.LENGTH_SHORT).show();
    }
}