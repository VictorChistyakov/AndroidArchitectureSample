package com.sample.arc.myapplication;


import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

public class CompoundViewModel extends ViewModel{
    LiveData<Compound> compoundMutableLiveData;
    MutableLiveData<Integer> livedata = new MutableLiveData<>();

    public CompoundViewModel() {
        livedata.setValue(0);
    }

    public void incrementValue(){
        livedata.setValue(livedata.getValue()+1);
    }

    public LiveData<Compound> getCompoundMutableLiveData(){
        if(compoundMutableLiveData == null){
            compoundMutableLiveData = Transformations.switchMap(livedata, new Function<Integer, LiveData<Compound>>() {
                @Override
                public LiveData<Compound> apply(Integer input) {
                    return retrieveData(input);
                }
            });
        }
        return compoundMutableLiveData;
    }

    private LiveData<Compound> retrieveData(Integer integer){
        MutableLiveData<Compound> result = new MutableLiveData<>();
        String state = integer % 2 == 0 ? "Even" : "Odd";
        Compound compound = new Compound(integer, state);
        result.setValue(compound);
        return result;
    }
}
