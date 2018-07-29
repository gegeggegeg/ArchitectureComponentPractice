package com.chen.peter.architecturecomponentpractice;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class DataAdapter extends PagedListAdapter<DataEntity,DataHolder> {

    private Context context;
    protected DataAdapter(Context context) {
        super(diffCallBack);
        this.context = context;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new DataHolder(inflater.inflate(R.layout.row_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        DataEntity dataEntity = getItem(position);
        if(dataEntity != null){
            holder.setTextView(dataEntity.getData());
        }
    }

    private static DiffUtil.ItemCallback<DataEntity> diffCallBack = new DiffUtil.ItemCallback<DataEntity>() {
        @Override
        public boolean areItemsTheSame(DataEntity oldItem, DataEntity newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @Override
        public boolean areContentsTheSame(DataEntity oldItem, DataEntity newItem) {
            return oldItem.getData().equals(newItem.getData());
        }
    };
}
