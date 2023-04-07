package com.example.imius.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.Singer;
import com.example.imius.repository.SingerRepository;
import com.example.imius.repository.TrendingRepository;

import java.util.ArrayList;
import java.util.List;

public class SingerViewModel extends AndroidViewModel {
    private SingerRepository repository;
    private RefreshLiveData<ArrayList<Singer>> liveData;

    public RefreshLiveData getLiveData(){
        return liveData;
    }
    public SingerViewModel (@NonNull Application application){
        super(application);

        repository = new SingerRepository();
        liveData = repository.getSinger();
    }

    public void refreshLiveData(){
        liveData.refresh();
    }
}
