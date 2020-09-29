package com.canhdinh.lib.helper;

import android.util.Log;

public class MyLog {
    public static void LogError(String mess) {
        Log.e("MyLog", mess);
    }

    public static void LogError(String tag, String mess) {
        Log.e(tag, mess);
    }

    public static void LogDebug(String mess) {
        Log.d("MyLog", mess);
    }

    public static void LogDebug(String tag, String mess) {
        Log.e(tag, mess);
    }

}
