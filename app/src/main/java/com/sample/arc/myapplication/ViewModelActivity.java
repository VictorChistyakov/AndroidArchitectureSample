package com.sample.arc.myapplication;

import android.app.Activity;
import android.arch.lifecycle.*;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncrementalLiveData.get().incrementValue();
            }
        });
        CompoundViewModel model = ViewModelProviders.of(this).get(CompoundViewModel.class);
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
