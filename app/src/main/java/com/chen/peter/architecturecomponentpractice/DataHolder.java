package com.chen.peter.architecturecomponentpractice;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class DataHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    public DataHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textData);
    }

    public void setTextView(String text){
        textView.setText(text);
    }

}
