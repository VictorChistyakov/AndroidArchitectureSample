package com.sample.arc.myapplication;

import android.arch.lifecycle.LiveData;
import android.support.annotation.MainThread;

public class IncrementalLiveData extends LiveData<Integer> {

    private static IncrementalLiveData sInstance = null;

    @MainThread
    public static IncrementalLiveData get() {
        if (sInstance == null) {
            sInstance = new IncrementalLiveData();
        }
        return sInstance;
    }

    @Override
    protected void onActive() {

    }

    @Override
    protected void onInactive() {

    }

    public void incrementValue(){
        Integer value = getValue();
        setValue(value == null ? 1 : value + 1);
    }
}
