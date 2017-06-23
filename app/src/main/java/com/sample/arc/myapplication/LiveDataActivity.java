package com.sample.arc.myapplication;

import android.app.Activity;
import android.arch.lifecycle.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LiveDataActivity extends android.arch.lifecycle.LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        final TextView txtCounter = (TextView) findViewById(R.id.txtCounter);
        Button btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncrementalLiveData.get().incrementValue();
            }
        });
        IncrementalLiveData.get().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                txtCounter.setText(integer.toString());
            }
        });

    }

    public static void launchLiveDataActivity(Activity context, boolean shouldCheckState){
        Intent intent = new Intent(context, LiveDataActivity.class);
        intent.putExtra("shouldCheckState", shouldCheckState);
        context.startActivity(intent);
    }

}
