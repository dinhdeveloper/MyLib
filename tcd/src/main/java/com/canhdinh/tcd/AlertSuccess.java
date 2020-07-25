package com.canhdinh.tcd;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlertSuccess {

    public static void showAlertSuccess(Context context, String titleText, String contentText){
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .show();
    }

    public static void showAlertSuccess(Context context, String titleText){
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(titleText)
                .show();
    }
}
