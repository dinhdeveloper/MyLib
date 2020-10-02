package com.canhdinh.repository;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class Repository<T>  {
    public MutableLiveData<List<T>> getData(){
        MutableLiveData<List<T>> data = new MutableLiveData<List<T>>();
        return data;
    }
}
