package com.canhdinh.lib.helper;

import android.content.Context;
import android.widget.Toast;

public class MyToast  {
    public static void show(Context context,String mess){
       Toast toast = Toast.makeText(context, mess, Toast.LENGTH_SHORT);
        toast.show();
    }
}
