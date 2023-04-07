package com.example.imius.repository;

import com.example.imius.api.API;
import com.example.imius.livedata.RefreshLiveData;
import com.example.imius.model.Singer;
import com.example.imius.service.DataService;

import java.util.ArrayList;


public class SingerRepository {
    private DataService dataService;
    public SingerRepository(){
        this.dataService = API.getAccount().create(DataService.class);
    }

    public RefreshLiveData<ArrayList<Singer>> getSinger(){
        RefreshLiveData<ArrayList<Singer>> data = new RefreshLiveData<>(callback -> {
            dataService
        });
        return data;
    }
}
