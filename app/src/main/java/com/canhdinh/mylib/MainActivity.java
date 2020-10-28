package com.canhdinh.mylib;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.canhdinh.SliderActivity;
import com.canhdinh.lib.alert.AlertConfirm;
import com.canhdinh.lib.alert.AlertDialog;
import com.canhdinh.lib.alert.AlertError;
import com.canhdinh.lib.alert.AlertSuccess;
import com.canhdinh.lib.colorpicker.ColorPickerDialog;
import com.canhdinh.lib.cookiebar.CookieBar;
import com.canhdinh.lib.countdownview.CountDownView;
import com.canhdinh.lib.edittext.FormattedEditText;
import com.canhdinh.lib.helper.MyToast;
import com.canhdinh.lib.kalert.KAlertDialog;
import com.canhdinh.lib.ksnack.KSnack;
import com.canhdinh.lib.ksnack.KSnackBarEventListener;
import com.canhdinh.lib.ksnack.Slide;
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
    Button search, select_image,
            loadmore, noty, btnTop,
            btnBottom, btnCustomAnim,
            startcountdown, button1,
            slider,imageslider,kAlert;
    FormattedEditText formattedEditText_simple, formattedEditText;
    CountDownView view_count_down;

    private KSnack kSnack;
    int DefaultColor ;
    String hexColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kSnack = new KSnack(MainActivity.this);

        Button snackAlert = findViewById(R.id.snackAlert);
        Button set_date_button = findViewById(R.id.set_date_button);
        PinTextView pinview = findViewById(R.id.pinview);
        Button showConfilm = findViewById(R.id.showConfilm);
        switchButton = findViewById(R.id.switch_button);
        formattedEditText_simple = findViewById(R.id.formattedEditText_simple);
        formattedEditText = findViewById(R.id.formattedEditText);
        search = findViewById(R.id.search);
        select_image = findViewById(R.id.select_image);
        loadmore = findViewById(R.id.loadmore);
        noty = findViewById(R.id.noty);
        btnTop = findViewById(R.id.btnTop);
        btnBottom = findViewById(R.id.btnBottom);
        btnCustomAnim = findViewById(R.id.btnCustomAnim);
        startcountdown = findViewById(R.id.startcountdown);
        view_count_down = findViewById(R.id.view_count_down);
        button1 = findViewById(R.id.button1);
        slider = findViewById(R.id.slider);
        imageslider = findViewById(R.id.imageslider);
        kAlert = findViewById(R.id.kAlert);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.myLayout);

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
                .setType(SnackAlert.SUCCESS)
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
        loadmore.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoadMoreActivity.class));
        });
        noty.setOnClickListener(v -> {
            kSnack.setListener(new KSnackBarEventListener() {
                @Override
                public void showedSnackBar() {
                    System.out.println("Showed");
                }

                @Override
                public void stoppedSnackBar() {
                    System.out.println("Stopped");
                }
            })
                    .setAction("Click", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "kSnack", Toast.LENGTH_SHORT).show();
                            kSnack.dismiss();
                        }
                    })
                    .setButtonTextColor(R.color.colorAccent)
                    .setMessage("This is KSnack !")
                    .setAnimation(Slide.Up.getAnimation(kSnack.getSnackView()), Slide.Down.getAnimation(kSnack.getSnackView()))
                    .show();
        });

        btnTop.setOnClickListener(v -> {
            CookieBar.build(MainActivity.this)
                    .setTitle("Đơn hàng mới")
                    .setTitleColor(R.color.progressColor)
                    .setMessage("Bạn có đơn hàng mới")
                    .setIcon(R.drawable.ic_baseline_notifications_48)
                    .setCookiePosition(CookieBar.TOP)
                    .setBackgroundColor(R.color.color_info)
                    .setDuration(5000).show();
        });

        btnBottom.setOnClickListener(v -> {
            CookieBar.build(MainActivity.this)
                    .setDuration(5000)
                    .setTitle("Thành công")
                    .setIcon(R.mipmap.ic_launcher)
                    .setMessage("Thêm sản phẩm thành công")
                    .setBackgroundColor(R.color.colorPrimary)
                    .setActionColor(R.color.color_danger)
                    .setTitleColor(R.color.color_info)
                    .setCookiePosition(CookieBar.BOTTOM)
                    .setAction("Xong", () -> Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show())
                    .show();
        });

        btnCustomAnim.setOnClickListener(v -> {
            CookieBar.build(MainActivity.this)
                    .setTitle("Đơn hàng mới")
                    .setMessage("Bạn có đơn hàng mới")
                    .setIcon(R.drawable.ic_baseline_notifications_48)
                    .setMessageColor(R.color.white)
                    .setBackgroundColor(R.color.color_info)
                    .setDuration(5000)
                    .setAnimationIn(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                    .setAnimationOut(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                    .show();
        });

        startcountdown.setOnClickListener(v -> {
            view_count_down.setStartDuration(3000); // 1h =3601000
            view_count_down.start();
        });

        view_count_down.setListener(() -> {
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        });

        button1.setOnClickListener(v -> {
            openDialog(false);
        });

        slider.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SlideActivity.class));
        });
        imageslider.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SliderActivity.class));
        });

        kAlert.setOnClickListener(v -> {
            AlertSuccess.showAlertSuccess(MainActivity.this,"Không tải được dữ liệu");
        });
    }

    void openDialog(boolean supportsAlpha) {
        ColorPickerDialog dialog = new ColorPickerDialog(MainActivity.this, DefaultColor, supportsAlpha, new ColorPickerDialog.OnPickerListener() {
            @Override
            public void onOk(ColorPickerDialog dialog, int color) {
                DefaultColor = color;
                hexColor = String.format("%06X", (0xFFFFFF & color));
                Toast.makeText(MainActivity.this, hexColor, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(ColorPickerDialog dialog) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
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