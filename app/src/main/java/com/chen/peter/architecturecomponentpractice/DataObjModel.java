package com.chen.peter.architecturecomponentpractice;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;


import java.util.List;

public class DataObjModel extends AndroidViewModel{
    private Application application;
    private DataRepository mRepository;
    private LiveData<PagedList<DataEntity>> pagedList;

    public DataObjModel(@NonNull Application application) {
        super(application);
        this.application = application;
        mRepository = new DataRepository(application);
        mRepository.initData();
        pagedList = new LivePagedListBuilder<>(mRepository.getSource(),20).build();
    }

    public LiveData<List<DataEntity>> getRoomData(){
        return mRepository.getData();
    }

    public void insertData(String data){
        mRepository.InsertData(data);
    }

    public LiveData<PagedList<DataEntity>> getPagedList() {
        return pagedList;
    }
}
