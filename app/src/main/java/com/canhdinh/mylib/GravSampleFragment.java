package com.canhdinh.mylib;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.canhdinh.lib.grav.GravView;

public class GravSampleFragment extends Fragment {

    public static final String KEY_LAYOUT = "KEY_LAYOUT";
    private int layout;
    GravView gravView;

    public static GravSampleFragment newInstance(int layout) {
        Bundle args = new Bundle();
        args.putInt(KEY_LAYOUT, layout);
        GravSampleFragment fragment = new GravSampleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = getArguments().getInt(KEY_LAYOUT);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gravView = getView().findViewById(R.id.grav);
    }

    @Override
    public void onPause() {
        super.onPause();
        gravView.stop();
    }
}