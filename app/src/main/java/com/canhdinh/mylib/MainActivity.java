package com.canhdinh.mylib;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.canhdinh.lib.alert.CircleProgress;
import com.canhdinh.lib.alert.CustomAlertDialog;
import com.canhdinh.lib.alert.JumpBall;
import com.canhdinh.lib.circlemenu.CircleMenuView;
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
import com.canhdinh.lib.munti_select.MultiSelectDialog;
import com.canhdinh.lib.munti_select.MultiSelectModel;
import com.canhdinh.lib.snackalert.SnackAlert;
import com.canhdinh.lib.spinnerdatepicker.DatePicker;
import com.canhdinh.lib.spinnerdatepicker.DatePickerDialog;
import com.canhdinh.lib.spinnerdatepicker.SpinnerDatePickerDialogBuilder;
import com.canhdinh.lib.textview.PinTextView;
import com.canhdinh.lib.toasty.Toasty;
import com.canhdinh.lib.togglebutton.SwitchButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            slider, imageslider, kAlert,
            passcode;

    Button grav, robot, ball_wave, falcon, bubble, path;
    FormattedEditText formattedEditText_simple, formattedEditText;
    CountDownView view_count_down;
    CircleProgress circleProgress;
    JumpBall jump_ball;

    private String TAG = "Cancel";

    Button show_dialog_btn;

    MultiSelectDialog multiSelectDialog;

    private KSnack kSnack;
    int DefaultColor;
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
        passcode = findViewById(R.id.passcode);
        circleProgress = findViewById(R.id.circleProgress);
        jump_ball = findViewById(R.id.jump_ball);
        grav = findViewById(R.id.grav_v);
        robot = findViewById(R.id.robot);
        ball_wave = findViewById(R.id.ball_wave);
        falcon = findViewById(R.id.falcon);
        bubble = findViewById(R.id.bubble);
        path = findViewById(R.id.path);
        Button waterview = findViewById(R.id.waterview);

        show_dialog_btn = findViewById(R.id.show_dialog);

        findViewById(R.id.button_error_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.error(MainActivity.this, "Lỗi", Toasty.LENGTH_SHORT, true).show();
            }
        });
        findViewById(R.id.button_success_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toasty.success(MainActivity.this, "Thành công", Toasty.LENGTH_SHORT, true).show();
            }
        });

        waterview.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, WareViewActivity.class));
        });

        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationEnd| index: " + index);
            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClick| index: " + index);
                return true;
            }

            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationEnd| index: " + index);
            }
        });

        //preselected Ids of Country List
        final ArrayList<Integer> alreadySelectedCountries = new ArrayList<>();
        alreadySelectedCountries.add(1);
        alreadySelectedCountries.add(3);
        alreadySelectedCountries.add(4);
        alreadySelectedCountries.add(7);

        //List of Countries with Name and Id
        ArrayList<MultiSelectModel> listOfCountries = new ArrayList<>();
        listOfCountries.add(new MultiSelectModel(1, "INDIA"));
        listOfCountries.add(new MultiSelectModel(2, "USA"));
        listOfCountries.add(new MultiSelectModel(3, "UK"));
        listOfCountries.add(new MultiSelectModel(4, "UAE"));
        listOfCountries.add(new MultiSelectModel(5, "JAPAN"));
        listOfCountries.add(new MultiSelectModel(6, "SINGAPORE"));
        listOfCountries.add(new MultiSelectModel(7, "CHINA"));
        listOfCountries.add(new MultiSelectModel(8, "RUSSIA"));
        listOfCountries.add(new MultiSelectModel(9, "BANGLADESH"));
        listOfCountries.add(new MultiSelectModel(10, "BELGIUM"));
        listOfCountries.add(new MultiSelectModel(11, "DENMARK"));
        listOfCountries.add(new MultiSelectModel(12, "GERMANY"));
        listOfCountries.add(new MultiSelectModel(13, "HONG KONG"));
        listOfCountries.add(new MultiSelectModel(14, "INDONESIA"));
        listOfCountries.add(new MultiSelectModel(15, "NETHERLAND NETHERLAND NETHERLAND NETHERLAND"));
        listOfCountries.add(new MultiSelectModel(16, "NEW ZEALAND"));
        listOfCountries.add(new MultiSelectModel(17, "PORTUGAL"));
        listOfCountries.add(new MultiSelectModel(18, "KUWAIT"));
        listOfCountries.add(new MultiSelectModel(19, "QATAR"));
        listOfCountries.add(new MultiSelectModel(20, "SAUDI ARABIA"));
        listOfCountries.add(new MultiSelectModel(21, "SRI LANKA"));
        listOfCountries.add(new MultiSelectModel(130, "CANADA"));

        //MultiSelectModel
        multiSelectDialog = new MultiSelectDialog()
                .title("Chọn đất nước") //setting title for dialog
                .titleSize(20)
                .positiveText("Chọn")
                .negativeText("Hủy bỏ")
                .setMinSelectionLimit(0)
                .setMaxSelectionLimit(listOfCountries.size())
                .preSelectIDsList(alreadySelectedCountries) //List of ids that you need to be selected
                .multiSelectList(listOfCountries) // the multi select model list with ids and name
                .onSubmit(new MultiSelectDialog.SubmitCallbackListener() {
                    @Override
                    public void onSelected(ArrayList<Integer> selectedIds, ArrayList<String> selectedNames, String dataString) {
                        //will return list of selected IDS
                        for (int i = 0; i < selectedIds.size(); i++) {
                            Toast.makeText(MainActivity.this, "Selected Ids : " + selectedIds.get(i) + "\n" +
                                    "Selected Names : " + selectedNames.get(i) + "\n" +
                                    "DataString : " + dataString, Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "Dialog cancelled");

                    }
                });
        show_dialog_btn.setOnClickListener(view -> {

            multiSelectDialog.show(getSupportFragmentManager(), "multiSelectDialog");
        });


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
            new CustomAlertDialog(MainActivity.this, CustomAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("You won't be able to recover this file!")
                    .setConfirmText("Delete!")
                    .setConfirmClickListener(new CustomAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(CustomAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();

                            new CustomAlertDialog(MainActivity.this, CustomAlertDialog.WARNING_TYPE)
                                    .setTitleText("Are you sure?")
                                    .setContentText("Won't be able to recover this file!")
                                    .setConfirmText("Yes,delete it!")
                                    .setConfirmClickListener(new CustomAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(CustomAlertDialog sDialog) {
                                            sDialog
                                                    .setTitleText("Deleted!")
                                                    .setContentText("Your imaginary file has been deleted!")
                                                    .setConfirmText("OK")
                                                    .setConfirmClickListener(null)
                                                    .changeAlertType(CustomAlertDialog.SUCCESS_TYPE);
                                        }
                                    })
                                    .show();
                        }
                    })
                    .setCancelButton("Cancel", new CustomAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(CustomAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            new CustomAlertDialog(MainActivity.this, CustomAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Something went wrong!")
                                    .show();
                        }
                    })
                    .show();
        });

        slider.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SlideActivity.class));
        });
        imageslider.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SliderActivity.class));
        });

        kAlert.setOnClickListener(v -> {
            AlertSuccess.showAlertSuccess(MainActivity.this, "Không tải được dữ liệu");
        });

        passcode.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, PassCodeActivity.class));
        });

        jump_ball.setOnClickListener(view -> {
            jump_ball.start();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    jump_ball.finish();
                }
            }, 1000);
        });

        grav.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AnimationActivity.class));
        });

    }

    public void addFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null && !fragment.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.root, fragment);

            // skip add fragment to back stack if it is first fragment
            if (addToBackStack) {
                transaction.addToBackStack(null);
            } else {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            transaction.commitAllowingStateLoss();
        }
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null && !fragment.isAdded()) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.root, fragment);

            // skip add fragment to back stack if it is first fragment
            if (addToBackStack) {
                transaction.addToBackStack(null);
            } else {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            transaction.commitAllowingStateLoss();
        }
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