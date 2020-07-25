package com.canhdinh.tcd;

import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AlertConfirmSuccess {

    private static ConfirmClickListener listener;
    public interface ConfirmClickListener {
        void setConfirmClickListener(SweetAlertDialog dialog);
    }
    public void setConfirmClickListener(ConfirmClickListener listener) {
        this.listener = listener;
    }

    public static void showAlertConfirmSuccess(Context context, String titleText, String contentText, String confirmText) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        if (listener!=null)
                        {
                            sDialog.dismissWithAnimation();
                            listener.setConfirmClickListener(sDialog);
                        }
                    }
                })
                .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    public static void showAlertConfirmSuccess(Context context, String titleText, String contentText, String confirmText, final ConfirmClickListener clicklistener) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        clicklistener.setConfirmClickListener(sDialog);
//                        sDialog
//                                .setTitleText("Deleted!")
//                                .setContentText("Your imaginary file has been deleted!")
//                                .setConfirmText("OK")
//                                .setConfirmClickListener(null)
//                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .show();
    }
}
