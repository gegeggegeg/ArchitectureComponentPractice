package com.chen.peter.architecturecomponentpractice;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        mDataModel.setTextHello("Hello Peter, this is from Android");
        mDataModel.setTextTest("This line is for testing ViewModel");

        mDataModel.getTextInput().observe(this,new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        mActivityMainBinding.setDataModel(mDataModel);
        listLiveData = mDataModel.getRoomData();
        Button button = findViewById(R.id.Btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: insert data "+mDataModel.getTextEditText());
                mDataModel.insertData(mDataModel.getTextEditText());
            }
        });

        listLiveData.observe(this, new Observer<List<DataEntity>>() {
            @Override
            public void onChanged(@Nullable List<DataEntity> dataEntities) {
                Log.d(TAG, "onChanged: Observed data changed");
                mDataModel.setTextTest(listLiveData.getValue().get(new Random().nextInt(20)).getData());
                mActivityMainBinding.setDataModel(mDataModel);
            }
        });
    }
}
