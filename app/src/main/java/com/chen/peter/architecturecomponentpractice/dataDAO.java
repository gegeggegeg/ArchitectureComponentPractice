package com.chen.peter.architecturecomponentpractice;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Dao
public interface dataDAO {

    @Insert
    public long insertData(DataEntity data);

    @Update
    public int updateData(DataEntity data);

    @Delete
    public void deleteData(DataEntity data);

    @Query("SELECT * FROM dataTable")
    public LiveData<List<DataEntity>> selectAll();

    @Query("SELECT * FROM dataTable")
    public android.arch.paging.DataSource.Factory<Integer,DataEntity> getPagerAll();

}
