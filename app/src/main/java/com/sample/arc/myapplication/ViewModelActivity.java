package com.sample.arc.myapplication;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewModelActivity extends android.arch.lifecycle.LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);
        final TextView txtTransformation = (TextView) findViewById(R.id.txtTransformation);
        Button btnIncrease = (Button) findViewById(R.id.btnIncrease);
        final CompoundViewModel model = ViewModelProviders.of(this).get(CompoundViewModel.class);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.incrementValue();
            }
        });

        model.getCompoundMutableLiveData().observe(this, new Observer<Compound>() {
            @Override
            public void onChanged(@Nullable Compound compound) {
                txtTransformation.setText(compound.counter + " " + compound.state);
            }
        });
    }

    public static void launchViewModelActivity(Activity context){
        Intent intent = new Intent(context, ViewModelActivity.class);
        context.startActivity(intent);
    }
}
