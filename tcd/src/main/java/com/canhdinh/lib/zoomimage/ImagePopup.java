package com.canhdinh.lib.zoomimage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.canhdinh.lib.R;

import java.io.File;

public class ImagePopup extends androidx.appcompat.widget.AppCompatImageView {
    private Context context;
    private PopupWindow popupWindow;
    View layout;
    private ImageView imageView;

    private int windowHeight = 0;
    private int windowWidth = 0;
    private boolean imageOnClickClose;
    private boolean hideCloseIcon;
    private boolean fullScreen;

    private int backgroundColor = Color.parseColor("#FFFFFF");


    public ImagePopup(Context context) {
        super(context);
        this.context = context;
    }

    public ImagePopup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    /**
     * Close Options
     **/

    public void setImageOnClickClose(boolean imageOnClickClose) {
        this.imageOnClickClose = imageOnClickClose;
    }


    public boolean isImageOnClickClose() {
        return imageOnClickClose;
    }

    public boolean isHideCloseIcon() {
        return hideCloseIcon;
    }

    public void setHideCloseIcon(boolean hideCloseIcon) {
        this.hideCloseIcon = hideCloseIcon;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public void initiatePopup(Drawable drawable) {

        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup));

            layout.setBackgroundColor(getBackgroundColor());

            imageView = (ImageView) layout.findViewById(R.id.imageView);
            imageView.setImageDrawable(drawable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initiatePopupWithPicasso(String imageUrl) {

        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup));

            layout.setBackgroundColor(getBackgroundColor());

            imageView = (ImageView) layout.findViewById(R.id.imageView);

            //Picasso.get().load(imageUrl).into(imageView);
            Glide.with(context).load(imageUrl).error(R.drawable.no_image_full).into(imageView);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ImagePopup ", e.getMessage());

        }
    }

    public void initiatePopupWithPicasso(Uri imageUri) {

        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup));

            layout.setBackgroundColor(getBackgroundColor());

            imageView = (ImageView) layout.findViewById(R.id.imageView);

            //Picasso.get().load(imageUri).into(imageView);
            Glide.with(context).load(imageUri).error(R.drawable.no_image_full).into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ImagePopup ", e.getMessage());

        }
    }

    public void initiatePopupWithPicasso(File imageFile) {

        try {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup));

            layout.setBackgroundColor(getBackgroundColor());

            imageView = (ImageView) layout.findViewById(R.id.imageView);
            Glide.with(context).load(imageFile).error(R.drawable.no_image_full).into(imageView);
//            Picasso.get()
//                    .load(imageFile)
//                    .into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ImagePopup ", e.getMessage());
        }
    }

    /**
     * optimize version
     * @param imageUrl
     */
    public void initiatePopupWithGlide(String imageUrl) {

        try {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            layout = inflater.inflate(R.layout.popup, (ViewGroup) findViewById(R.id.popup));

            layout.setBackgroundColor(getBackgroundColor());

            imageView = (ImageView) layout.findViewById(R.id.imageView);

            Glide.with(context)
                    .load(imageUrl)
                    .into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ImagePopup ", e.getMessage());
        }
    }


    public void setLayoutOnTouchListener(OnTouchListener onTouchListener) {
        layout.setOnTouchListener(onTouchListener);
    }

    public void viewPopup() {

        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);


        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        if(isFullScreen()) {
            popupWindow = new PopupWindow(layout, (width), (height), true);
        }else {
            if (windowHeight != 0 || windowWidth != 0) {
                width = windowWidth;
                height = windowHeight;
                popupWindow = new PopupWindow(layout, (width), (height), true);
            } else {
                popupWindow = new PopupWindow(layout, (int) (width * .8), (int) (height * .6), true);
            }
        }


        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        ImageView closeIcon = (ImageView) layout.findViewById(R.id.closeBtn);

        if (isHideCloseIcon()) {
            closeIcon.setVisibility(View.GONE);
        }
        closeIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        if (isImageOnClickClose()) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }

        dimBehind(popupWindow);

    }


    public static void dimBehind(PopupWindow popupWindow) {
        try {
            View container;
            if (popupWindow.getBackground() == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    container = (View) popupWindow.getContentView().getParent();
                } else {
                    container = popupWindow.getContentView();
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    container = (View) popupWindow.getContentView().getParent().getParent();
                } else {
                    container = (View) popupWindow.getContentView().getParent();
                }
            }
            Context context = popupWindow.getContentView().getContext();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
            p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            p.dimAmount = 0.3f;
            wm.updateViewLayout(container, p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
///** Set Image Height, Width & Background Color as you want **/
//
//        Picasso.setSingletonInstance(new Picasso.Builder(this).build());
//
//                Log.e("Width",""+Resources.getSystem().getDisplayMetrics().widthPixels);
//final ImagePopup imagePopup = new ImagePopup(this);
//        imagePopup.setBackgroundColor(Color.BLACK);
//        imagePopup.setFullScreen(false);
//        imagePopup.setHideCloseIcon(true);
//        imagePopup.setImageOnClickClose(true);
//
//
//final String photoUrl = "http://pngriver.com/wp-content/uploads/2017/12/download-Android-Technology-logo-PNG-transparent-images-transparent-backgrounds-PNGRIVER-COM-Android-Mobile-App-Development-Company-In-India-brillmindztech-39975001-800-799.png";
//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//
//        Picasso.get().load(photoUrl).into(imageView);
//        // to use it when the same photo are in the image
//        // imagePopup.initiatePopup(imageView.getDrawable());
//
//        // to download the image from url if you want different resolution or different image
//        imagePopup.initiatePopupWithPicasso(photoUrl);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        /** Initiate Popup view **/
//        imagePopup.viewPopup();
//        }
//        });