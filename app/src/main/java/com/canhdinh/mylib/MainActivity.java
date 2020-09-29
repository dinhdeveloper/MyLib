package com.canhdinh.mylib;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.canhdinh.lib.helper.MyToast;
import com.canhdinh.lib.loadingbutton.ButtonLoading;
import com.canhdinh.lib.snackalert.SnackAlert;
import com.canhdinh.lib.spinnerdatepicker.DatePicker;
import com.canhdinh.lib.spinnerdatepicker.DatePickerDialog;
import com.canhdinh.lib.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import com.canhdinh.lib.textview.PinTextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , DatePickerDialog.OnDateCancelListener{
    ButtonLoading buttonLoading;
    LinearLayout root;
    SimpleDateFormat simpleDateFormat;
    PinTextView pinview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button snackAlert = findViewById(R.id.snackAlert);
        Button set_date_button = findViewById(R.id.set_date_button);
        PinTextView pinview = findViewById(R.id.pinview);


        simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.US);
        snackAlert.setOnClickListener(v -> new SnackAlert(MainActivity.this)
                .setTitle("Success")
                .setMessage("This is a success message")
                .setDurationAnimationShow(600).show());

        set_date_button.setOnClickListener(v -> {
            showDate(1980, 0, 1, R.style.DatePickerSpinner);
        });

        pinview.setPinViewEventListener((pinview1, fromUser) -> {
            MyToast.show(MainActivity.this,pinview1.getValue());
        });

        root=findViewById(R.id.root);
        buttonLoading=findViewById(R.id.loadingbutton);
        buttonLoading.setRoot(buttonLoading,this,root);
        buttonLoading.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
            @Override
            public void onClick() {
                //...
            }

            @Override
            public void onStart() {

                //...
            }

            @Override
            public void onFinish() {
                //...

            }
        });

    }
    @Override
    public void onBackPressed() {

        buttonLoading.cancel();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        MyToast.show(MainActivity.this,simpleDateFormat.format(calendar.getTime()));
    }

    @Override
    public void onCancelled(DatePicker view) {
        //TODO hủy kết quả, xóa ngày
    }
    @VisibleForTesting
    void showDate(int year, int monthOfYear, int dayOfMonth, int spinnerTheme) {
        new SpinnerDatePickerDialogBuilder()
                .context(MainActivity.this)
                .callback(MainActivity.this)
                .onCancel(MainActivity.this)
                .spinnerTheme(spinnerTheme)
                .defaultDate(year, monthOfYear, dayOfMonth)
                .build()
                .show();
    }
}