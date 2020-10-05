package com.canhdinh.mylib.model;

public class BookingResultModel extends BaseResponseModel  {
    private String id;
    private String image_upload;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_upload() {
        return image_upload;
    }

    public void setImage_upload(String image_upload) {
        this.image_upload = image_upload;
    }
}
