package com.canhdinh;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.canhdinh.lib.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.canhdinh.lib.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.canhdinh.lib.smarteist.autoimageslider.SliderAnimations;
import com.canhdinh.lib.smarteist.autoimageslider.SliderView;
import com.canhdinh.mylib.R;
import com.canhdinh.mylib.adapter.SliderAdapterExample;
import com.canhdinh.mylib.model.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {
    SliderView sliderView;
    private SliderAdapterExample adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        sliderView = findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(this);
        addNewItem();
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Toast.makeText(SliderActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addNewItem() {
        SliderItem sliderItem = new SliderItem("A","https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        SliderItem sliderItem1 = new SliderItem("B","https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        SliderItem sliderItem2 = new SliderItem("C","https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        SliderItem sliderItem3 = new SliderItem("D","https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        SliderItem sliderItem4 = new SliderItem("E","https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        SliderItem sliderItem5 = new SliderItem("F","https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");

        adapter.addItem(sliderItem);
        adapter.addItem(sliderItem1);
        adapter.addItem(sliderItem2);
        adapter.addItem(sliderItem3);
        adapter.addItem(sliderItem4);
        adapter.addItem(sliderItem5);
    }
}