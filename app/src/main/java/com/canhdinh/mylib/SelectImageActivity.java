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
import com.canhdinh.mylib.api.RequestUpdateResultPayment;
import com.canhdinh.mylib.model.BaseResponseModel;

import java.io.File;
import java.util.List;

public class SelectImageActivity extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate, BSImagePicker.OnSelectImageCancelledListener {
    private RoundTextView btnUpdateResult, btnSubmit;
    private ImageView imvImageResult;
    String payment_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        btnUpdateResult = findViewById(R.id.btnUpdateResult);
        btnSubmit = findViewById(R.id.btnSubmit);
        imvImageResult = findViewById(R.id.imvImageResult);

        btnUpdateResult.setOnClickListener(view -> {
            BSImagePicker pickerDialog = new BSImagePicker.Builder("com.canhdinh.mylib.fileprovider")
                    .build();
            pickerDialog.show(getSupportFragmentManager(), "picker");
        });

        btnSubmit.setOnClickListener(view -> {
            RequestUpdateResultPayment.ApiParams params = new RequestUpdateResultPayment.ApiParams();
            params.id_booking = "74";
            params.payment_image = payment_image;
            params.type_manager = "update_result_payment";

            AppProvider.getApiManagement().call(RequestUpdateResultPayment.class, params, new ApiRequest.ApiCallback<BaseResponseModel>() {
                @Override
                public void onSuccess(BaseResponseModel body) {
                    if (!TextUtils.isEmpty(body.getSuccess()) && body.getSuccess().equalsIgnoreCase("true")) {
                        MyLog.LogDebug(body.getMessage());
                    } else {
                        MyLog.LogDebug(body.getMessage());
                    }
                }

                @Override
                public void onError(ErrorApiResponse error) {
                    Log.e("onError", error.message);
                }

                @Override
                public void onFail(ApiRequest.RequestError error) {
                    Log.e("onError", error.name());
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
    }

    @Override
    public void onCancelled(boolean isMultiSelecting, String tag) {
        Toast.makeText(this, "Selection is cancelled, Multi-selection is " + isMultiSelecting, Toast.LENGTH_SHORT).show();
    }
}