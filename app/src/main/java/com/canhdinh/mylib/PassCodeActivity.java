package com.canhdinh.mylib;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.canhdinh.lib.passview.PasscodeView;

public class PassCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_pass_code);

        PasscodeView passcodeView = (PasscodeView) findViewById(R.id.passcodeView);
        passcodeView.setCorrectInputTip("123456");
        passcodeView.setListener(new PasscodeView.PasscodeViewListener() {
            @Override
            public void onFail(String wrongNumber) {
                Toast.makeText(PassCodeActivity.this, "Sai", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String number) {
                if (number.equalsIgnoreCase(passcodeView.getCorrectInputTip())){
                    Toast.makeText(PassCodeActivity.this, "Đúng", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }

            }
        });
    }
}