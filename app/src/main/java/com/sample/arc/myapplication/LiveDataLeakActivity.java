package com.sample.arc.myapplication;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;

public class LiveDataLeakActivity extends android.arch.lifecycle.LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_leak);
        final LifecycleTextView txtCounter = (LifecycleTextView) findViewById(R.id.txtCounter);
        IncrementalLiveData.get().observe(txtCounter, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtCounter.setText(integer.toString());
            }
        });
    }

    public static void launchLiveDataLeakActivity(Activity context){
        Intent intent = new Intent(context, LiveDataLeakActivity.class);
        context.startActivity(intent);
    }
}
