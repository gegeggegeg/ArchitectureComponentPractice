package com.chen.peter.architecturecomponentpractice;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chen.peter.architecturecomponentpractice.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mActivityMainBinding;

    DataObjModel mDataModel;
    LiveData<List<DataEntity>> listLiveData;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mDataModel = ViewModelProviders.of(this).get(DataObjModel.class);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        final DataAdapter adapter = new DataAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        mDataModel.getPagedList().observe(this, new Observer<PagedList<DataEntity>>() {
            @Override
            public void onChanged(@Nullable PagedList<DataEntity> dataEntities) {
                adapter.submitList(dataEntities);
                recyclerView.setAdapter(adapter);
            }
        });
        mActivityMainBinding.setDataModel(mDataModel);
    }
}
