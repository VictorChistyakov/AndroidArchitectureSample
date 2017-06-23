package com.sample.arc.myapplication;

import android.app.Activity;
import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.TextView;

public class TransformationLiveDataActivity extends android.arch.lifecycle.LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation_live_data);
        final TextView txtCounter = (TextView) findViewById(R.id.txtCounter);
        final TextView txtTransformation = (TextView) findViewById(R.id.txtTransformation);
        Button btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncrementalLiveData.get().incrementValue();
            }
        });

        LiveData<String> transformation = Transformations.map(IncrementalLiveData.get(), new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return input % 2 == 0 ? "Even" : "Odd";
            }
        });
        transformation.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                txtCounter.setText(s);
            }
        });

        LiveData<Compound> compoundLiveData = Transformations.switchMap(IncrementalLiveData.get(), new Function<Integer, LiveData<Compound>>() {
            @Override
            public LiveData<Compound> apply(Integer input) {
                return retrieveData(input);
            }
        });
        compoundLiveData.observe(this, new Observer<Compound>() {
            @Override
            public void onChanged(@Nullable Compound compound) {
                txtTransformation.setText(compound.counter + " " + compound.state);
            }
        });

    }

    public static void launchTransformationLiveDataActivity(Activity context){
        Intent intent = new Intent(context, TransformationLiveDataActivity.class);
        context.startActivity(intent);
    }

    private LiveData<Compound> retrieveData(Integer integer){
        MutableLiveData<Compound> result = new MutableLiveData<>();
        String state = integer % 2 == 0 ? "Even" : "Odd";
        Compound compound = new Compound(integer, state);
        result.setValue(compound);
        return result;
    }

}
