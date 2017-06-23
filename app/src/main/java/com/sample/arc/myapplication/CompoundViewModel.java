package com.sample.arc.myapplication;


import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

public class CompoundViewModel extends ViewModel{
    LiveData<Compound> compoundMutableLiveData;

    public LiveData<Compound> getCompoundMutableLiveData(){
        if(compoundMutableLiveData == null){
            IncrementalLiveData.get().incrementValue();
            compoundMutableLiveData = Transformations.switchMap(IncrementalLiveData.get(), new Function<Integer, LiveData<Compound>>() {
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
