package com.canhdinh.mylib;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.LinearLayout;

import com.canhdinh.lib.alert.AlertConfirm;
import com.canhdinh.lib.alert.AlertDialog;
import com.canhdinh.lib.edittext.FormattedEditText;
import com.canhdinh.lib.helper.MyToast;
import com.canhdinh.lib.loadingbutton.ButtonLoading;
import com.canhdinh.lib.snackalert.SnackAlert;
import com.canhdinh.lib.spinnerdatepicker.DatePicker;
import com.canhdinh.lib.spinnerdatepicker.DatePickerDialog;
import com.canhdinh.lib.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import com.canhdinh.lib.textview.PinTextView;
import com.canhdinh.lib.togglebutton.SwitchButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DatePickerDialog.OnDateCancelListener {
    ButtonLoading buttonLoading;
    LinearLayout root;
    SimpleDateFormat simpleDateFormat;
    PinTextView pinview;
    SwitchButton switchButton;
    RecyclerView recyclerView;
    Button search,select_image;
    FormattedEditText formattedEditText_simple, formattedEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button snackAlert = findViewById(R.id.snackAlert);
        Button set_date_button = findViewById(R.id.set_date_button);
        PinTextView pinview = findViewById(R.id.pinview);
        Button showConfilm = findViewById(R.id.showConfilm);
        switchButton = findViewById(R.id.switch_button);
        formattedEditText_simple = findViewById(R.id.formattedEditText_simple);
        formattedEditText = findViewById(R.id.formattedEditText);
        recyclerView = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search);
        select_image = findViewById(R.id.select_image);

        showConfilm.setOnClickListener(v -> {

            switchButton.setChecked(true);
            switchButton.isChecked();
            switchButton.toggle();     //switch state
            switchButton.toggle(false);//switch without animation
            switchButton.setShadowEffect(true);//disable shadow effect
            switchButton.setEnabled(false);//disable button
            switchButton.setEnableEffect(false);//disable the switch animation
            switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                    //TODO do your job
                }
            });


//            AlertConfirm.showAlertConfirm(MainActivity.this,SUCCESS_TYPE ,"Xác nhận",
//                    "Tạo sản phẩm thành công", "xác nhận","hủy bỏ", new AlertConfirm.ConfirmClickListener() {
//                        @Override
//                        public void setConfirmClickListener(AlertDialog dialog) {
//                            dialog.dismissWithAnimation();
//                            new SnackAlert(MainActivity.this).setTitle("Thành công")
//                                    .setMessage("Thành công rồi").show();
//                        }
//
//                        @Override
//                        public void setCancelButton(AlertDialog dialog) {
//                            dialog.dismissWithAnimation();
//                        }
//                    });

            AlertConfirm.showAlertWarning(MainActivity.this, "Xác nhận", "Bạn chưa chọn gì đó?", new AlertConfirm.ConfirmClickListener() {
                @Override
                public void setConfirmClickListener(AlertDialog dialog) {
                    dialog.dismissWithAnimation();
                    new SnackAlert(MainActivity.this).setTitle("Chọn rồi")
                            .setType(SnackAlert.WARNING)
                            .setMessage("Thành công rồi").show();
                }

                @Override
                public void setCancelButton(AlertDialog dialog) {
                    dialog.dismissWithAnimation();
                }
            });
        });

        simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.US);
        snackAlert.setOnClickListener(v -> new SnackAlert(MainActivity.this)
                .setTitle("Success")
                .setMessage("This is a success message")
                .setDurationAnimationShow(600).show());

        set_date_button.setOnClickListener(v -> {
            showDate(1980, 0, 1, R.style.DatePickerSpinner);
        });

        pinview.setPinViewEventListener((pinview1, fromUser) -> {
            MyToast.show(MainActivity.this, pinview1.getValue());
        });

        root = findViewById(R.id.root);
        buttonLoading = findViewById(R.id.loadingbutton);
        buttonLoading.setRoot(buttonLoading, this, root);
        buttonLoading.setOnButtonLoadingListener(new ButtonLoading.OnButtonLoadingListener() {
            @Override
            public void onClick() {
                MyToast.show(getApplicationContext(), "onClick");
            }

            @Override
            public void onStart() {
                MyToast.show(getApplicationContext(), "onStart");
            }

            @Override
            public void onFinish() {
                MyToast.show(getApplicationContext(), "onFinish");
            }
        });


        formattedEditText_simple.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MyToast.show(MainActivity.this, formattedEditText_simple.getRealText());
            }
        });

        formattedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MyToast.show(MainActivity.this, formattedEditText.getRealText());
            }
        });

        search.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        });
        select_image.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SelectImageActivity.class));
        });
    }

    @Override
    public void onBackPressed() {

        buttonLoading.cancel();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        MyToast.show(MainActivity.this, simpleDateFormat.format(calendar.getTime()));
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