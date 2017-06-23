package com.sample.arc.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonLifecycle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LifecycleActivity.launchLifecycleActivity(MainActivity.this, false);
            }
        });
        findViewById(R.id.buttonLifecycleCheckState).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LifecycleActivity.launchLifecycleActivity(MainActivity.this, true);
            }
        });
        findViewById(R.id.buttonLiveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataActivity.launchLiveDataActivity(MainActivity.this, true);
            }
        });

        findViewById(R.id.buttonLiveDataLeak).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataLeakActivity.launchLiveDataLeakActivity(MainActivity.this);
            }
        });

        findViewById(R.id.buttonTransformation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransformationLiveDataActivity.launchTransformationLiveDataActivity(MainActivity.this);
            }
        });

        findViewById(R.id.buttonViewModel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewModelActivity.launchViewModelActivity(MainActivity.this);
            }
        });
    }

}


