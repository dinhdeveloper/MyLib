package com.canhdinh.tcd;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlertLoading {
    public static void showAlertLoading(Context context,String backgroundColor,String titleText){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(backgroundColor));
        pDialog.setTitleText(titleText);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void showAlertLoading(Context context,String backgroundColor){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(backgroundColor));
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void showAlertLoadingCancelable(Context context,String backgroundColor){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(backgroundColor));
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public static void showAlertLoadingCancelable(Context context,String backgroundColor,String titleText){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(backgroundColor));
        pDialog.setTitleText(titleText);
        pDialog.setCancelable(true);
        pDialog.show();
    }
}
