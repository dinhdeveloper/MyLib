package com.canhdinh.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.canhdinh.tcd.AlertConfirm;
import com.canhdinh.tcd.AlertConfirmSuccess;
import com.canhdinh.tcd.AlertLoading;
import com.canhdinh.tcd.AlertSuccess;
import com.canhdinh.tcd.MyToast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       AlertConfirmSuccess.showAlertConfirmSuccess(MainActivity.this, "test", "dadads", "asdasaffsff", new AlertConfirmSuccess.ConfirmClickListener() {
           @Override
           public void setConfirmClickListener(SweetAlertDialog dialog) {
               dialog
                       .setTitleText("Deleted!")
                       .setContentText("Your imaginary file has been deleted!")
                       .setConfirmText("OK")
                       .setConfirmClickListener(null)
                       .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
           }
       });

    }
}