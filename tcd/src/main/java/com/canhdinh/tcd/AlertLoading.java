package com.canhdinh.tcd;

import android.content.Context;
import android.graphics.Color;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlertLoading {
    public static void showAlertLoading(Context context,String colorLoading,String titleText){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setTitleText(titleText);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void showAlertLoading(Context context,String colorLoading){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public static void showAlertLoadingCancelable(Context context,String colorLoading){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public static void showAlertLoadingCancelable(Context context,String colorLoading,String titleText){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor(colorLoading));
        pDialog.setTitleText(titleText);
        pDialog.setCancelable(true);
        pDialog.show();
    }
}
