package com.canhdinh.lib.neumorphism;

import android.content.Context;
import android.util.DisplayMetrics;

public class DimensionsUtil {

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
/*
*
* <?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/colorAccent"
    android:clipChildren="false"
    tools:context=".MainActivity">

    <com.borutsky.neumorphism.NeumorphicFrameLayout
        android:id="@+id/firstBlock"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_marginBottom="30dp"
        app:background_color="@color/colorAccent"
        app:corner_radius="20dp"
        app:layout_constraintBottom_toTopOf="@id/secondBlock"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:shape="rectangle">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="@string/press_me" />

    </com.borutsky.neumorphism.NeumorphicFrameLayout>

    <com.borutsky.neumorphism.NeumorphicFrameLayout
        android:id="@+id/secondBlock"
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:clipChildren="false"
        android:text="Hello World!"
        app:background_color="@color/colorAccent"
        app:corner_radius="40dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:shape="rectangle"
        app:state="flat">

        <com.borutsky.neumorphism.NeumorphicFrameLayout
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:layout_gravity="center"
            app:background_color="@color/colorAccent"
            app:shape="circle"
            app:state="pressed" />

    </com.borutsky.neumorphism.NeumorphicFrameLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:clipChildren="false"
        android:gravity="center"
        android:orientation="horizontal"
        app:layout_constraintTop_toBottomOf="@id/secondBlock">

        <com.borutsky.neumorphism.NeumorphicFrameLayout
            android:id="@+id/flat"
            android:layout_width="80dp"
            android:layout_height="80dp"
            app:background_color="@color/colorAccent"
            app:corner_radius="20dp"
            app:shape="rectangle"
            app:state="flat">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="@string/flat" />

        </com.borutsky.neumorphism.NeumorphicFrameLayout>

        <com.borutsky.neumorphism.NeumorphicFrameLayout
            android:id="@+id/concave"
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_marginStart="20dp"
            app:background_color="@color/colorAccent"
            app:corner_radius="20dp"
            app:shape="rectangle"
            app:state="concave">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="@string/concave" />

        </com.borutsky.neumorphism.NeumorphicFrameLayout>

        <com.borutsky.neumorphism.NeumorphicFrameLayout
            android:id="@+id/convex"
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_marginStart="20dp"
            app:background_color="@color/colorAccent"
            app:corner_radius="20dp"
            app:shape="rectangle"
            app:state="convex">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="@string/convex" />

        </com.borutsky.neumorphism.NeumorphicFrameLayout>

        <com.borutsky.neumorphism.NeumorphicFrameLayout
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:layout_marginStart="20dp"
            app:background_color="@color/colorAccent"
            app:corner_radius="20dp"
            app:shape="rectangle"
            app:state="pressed">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="@string/pressed" />

        </com.borutsky.neumorphism.NeumorphicFrameLayout>


    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
*
*
*
* NeumorphicFrameLayout.State[] state1 = new NeumorphicFrameLayout.State[]{
            FLAT,
            CONCAVE
    };
    NeumorphicFrameLayout.State[] state2 = new NeumorphicFrameLayout.State[]{
            CONCAVE,
            CONVEX
    };
    NeumorphicFrameLayout.State[] state3 = new NeumorphicFrameLayout.State[]{
            PRESSED,
            CONVEX
    };
    NeumorphicFrameLayout.State[] state4 = new NeumorphicFrameLayout.State[]{
            CONVEX,
            FLAT
    };


    int current = 0;
    *
    * block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = current < 1 ? current + 1 : 0;
                ((NeumorphicFrameLayout) v).setState(state1[current]);
            }
        });
* */