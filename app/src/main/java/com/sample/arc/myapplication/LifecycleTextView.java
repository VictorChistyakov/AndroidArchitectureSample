package com.sample.arc.myapplication;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;


public class LifecycleTextView extends android.support.v7.widget.AppCompatTextView implements LifecycleRegistryOwner {

    LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public LifecycleTextView(Context context) {
        super(context);
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }


    public LifecycleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    public LifecycleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        lifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }


    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
