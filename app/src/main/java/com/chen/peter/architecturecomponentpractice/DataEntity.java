package com.chen.peter.architecturecomponentpractice;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "dataTable")
public class DataEntity {

    @PrimaryKey(autoGenerate = true)
    private long _id;

    @ColumnInfo (name = "data")
    private String data;

    public DataEntity(String data) {
        this.data = data;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
