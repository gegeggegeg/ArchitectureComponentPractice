package com.chen.peter.architecturecomponentpractice;

import android.app.AlertDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;


import com.chen.peter.architecturecomponentpractice.databinding.ActivityMainBinding;

import java.util.List;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        builder.setTitle("Enter data")
                .setView(editText)
                .setPositiveButton("insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDataModel.insertData(editText.getText().toString());
                    }
                })
                .setNegativeButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDataModel.deleteData(editText.getText().toString());
                    }
                });
        final AlertDialog dialog = builder.create();
        FloatingActionButton FAB = findViewById(R.id.FAB);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
}
