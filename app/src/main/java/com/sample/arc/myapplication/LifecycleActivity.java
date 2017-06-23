package com.sample.arc.myapplication;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LifecycleActivity extends FragmentActivity implements LifecycleRegistryOwner {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    boolean shouldCheckState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        shouldCheckState = getIntent().getBooleanExtra("shouldCheckState", false);
        findViewById(R.id.buttonStartWork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("LifecycleActivity", "Job started");
                        if(shouldCheckState && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.DESTROYED))
                            return;
                        ButtonFragment frag = ButtonFragment.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, "ButtonFragment").commit();
                        Log.d("LifecycleActivity", "Job completed");
                    }
                }, 2000);
            }
        });
    }

    public static void launchLifecycleActivity(Activity context, boolean shouldCheckState){
        Intent intent = new Intent(context, LifecycleActivity.class);
        intent.putExtra("shouldCheckState", shouldCheckState);
        context.startActivity(intent);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
