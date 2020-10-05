package com.canhdinh.mylib.api;

import android.text.TextUtils;

import com.canhdinh.api.ApiRequest;
import com.canhdinh.api.BaseApiParams;
import com.canhdinh.mylib.model.BaseResponseModel;
import com.canhdinh.mylib.model.BookingResultModel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

@ApiRequest.ApiName("update_result_payment")
public class RequestUpdateResultPayment extends ApiRequest<RequestUpdateResultPayment.Service, BaseResponseModel<BookingResultModel>, RequestUpdateResultPayment.ApiParams> {

    public RequestUpdateResultPayment() {
        super(RequestUpdateResultPayment.Service.class, RequestOrigin.NONE, Consts.HOST_API, Consts.MODE, Consts.TRUST_CERTIFICATE);
    }

    @Override
    protected void postAfterRequest(BaseResponseModel<BookingResultModel> result) throws Exception {

    }

    @Override
    protected Call<BaseResponseModel<BookingResultModel>> call(ApiParams params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (!TextUtils.isEmpty(params.payment_image)) {
            File fileAvatar = new File(params.payment_image);
            if (fileAvatar.exists()) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), fileAvatar);
                builder.addFormDataPart("payment_image", fileAvatar.getName(), fileBody);
            }
        }
        if (!TextUtils.isEmpty((params.id_booking))) {
            builder.addFormDataPart("id_booking", params.id_booking);
        }
        if (!TextUtils.isEmpty((params.type_manager))) {
            builder.addFormDataPart("type_manager", params.type_manager);
        }
        params.detect = "booking_manager";
        builder.addFormDataPart("detect", params.detect)
                .setType(MultipartBody.FORM);

        RequestBody requestBody = builder.build();
        return getService().call(requestBody);
    }

    public interface Service {
        @Headers(Consts.HEADES)
        @POST(Consts.REST_ENDPOINT)
        Call<BaseResponseModel<BookingResultModel>> call(@Body RequestBody params);
    }

    public static class ApiParams extends BaseApiParams {
        public String detect;
        public String id_booking;
        public String type_manager;
        public String payment_image ;
    }
}