package com.canhdinh.tcd;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlertError {
    public static void showAlertError(Context context, String titleText, String contentText){
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .show();
    }

    public static void showAlertError(Context context, String titleText){
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(titleText)
                .show();
    }
}
