package com.chen.peter.architecturecomponentpractice;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DataEntity.class},version = 1)
public abstract class DataRoomDatabase extends RoomDatabase {
    public abstract dataDAO datadao();
}
