package com.chen.peter.architecturecomponentpractice;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private static final String TAG = "DataRepository";
    private static dataDAO datadao;
    private final static String[] words =new String[]{"apple","dog","cat","bear","egg","frog","goat"
            ,"hen","ice","joke","kotlin","lemon","monster","nose","orange","pig","queen","rock","system","team"
            ,"ufo","water","year","xavier","zoo"};
    private static ArrayList<DataEntity> list;
    private Application application;

    public DataRepository(Application application) {
        this.application =application;
        list = new ArrayList<>();
    }

    public void initData(){
        DataRoomDatabase database = Room.databaseBuilder(application,DataRoomDatabase.class,"database").build();
        datadao = database.datadao();
        new initInsetTask().execute();
    }

    public List<DataEntity> getData(){
        return datadao.selectAll();
    }

    public DataSource.Factory<Integer,DataEntity> getSource(){
        return datadao.getPagerAll();
    }

    public void InsertData (String data){
        Log.d(TAG, "InsertData: insert data"+data);
        new InsertTask(data).execute();
    }

    public void deleteNamedData(String data){
        new DeleteTask().execute(data);
    }

    private static class initInsetTask extends AsyncTask<DataEntity,Void,Void>{

        @Override
        protected Void doInBackground(DataEntity... dataEntities) {
            if (datadao.selectAll().isEmpty()) {
                for (String s : words) {
                    DataEntity temp = new DataEntity(s);
                    long rowID = datadao.insertData(temp);
                    temp.set_id(rowID);
                    list.add(temp);
                }
            }
            return null;
        }
    }
    private static class InsertTask extends AsyncTask<DataEntity,Void,Void>{
        private String data;

        public InsertTask(String data) {
            super();
            this.data = data;
        }

        @Override
        protected Void doInBackground(DataEntity... dataEntities) {
            DataEntity dataEntity = new DataEntity(data);
            long rowID = datadao.insertData(dataEntity);
            dataEntity.set_id(rowID);
            Log.d(TAG, "doInBackground: data inserted");
            return null;
        }
    }

    public static class DeleteTask extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            datadao.deleteNamedData(strings[0]);
            return null;
        }
    }



}
