package com.canhdinh.lib.alert;

import android.content.Context;

public class AlertConfirm {
    private static ConfirmClickListener listener;

    public interface ConfirmClickListener {
        void setConfirmClickListener(AlertDialog dialog);
        void setCancelButton(AlertDialog dialog);
    }

    public void setConfirmClickListener(ConfirmClickListener listener) {
        this.listener = listener;
    }

    public static void showAlertConfirm(Context context, String titleText, String contentText, String confirmText) {
        new AlertDialog(context, AlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sDialog) {
                        if (listener!=null)
                        {
                            sDialog.dismissWithAnimation();
                            listener.setConfirmClickListener(sDialog);
                        }
                    }
                })
                .setCancelButton("Cancel", new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }

    public static void showAlertConfirm(Context context, String titleText, String contentText, String confirmText, final ConfirmClickListener clicklistener){
        new AlertDialog(context, AlertDialog.WARNING_TYPE)
                .setTitleText(titleText)
                .setContentText(contentText)
                .setConfirmText(confirmText)
                .setConfirmClickListener(new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sDialog) {
                        if (clicklistener!=null)
                        {
                            sDialog.dismissWithAnimation();
                            clicklistener.setConfirmClickListener(sDialog);
                        }
                    }
                })
                .setCancelButton("Cancel", new AlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(AlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}
