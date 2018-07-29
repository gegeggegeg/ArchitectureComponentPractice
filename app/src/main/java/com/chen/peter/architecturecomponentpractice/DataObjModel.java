package com.chen.peter.architecturecomponentpractice;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.List;

public class DataObjModel extends AndroidViewModel{
    private String textHello;
    private String textTest;
    private String textEditText;
    final private MutableLiveData<String> textInput;
    private Application application;
    private DataRepository mRepository;

    public DataObjModel(@NonNull Application application) {
        super(application);
        this.application = application;
        textInput = new MutableLiveData<>();
        mRepository = new DataRepository(application);
        mRepository.initData();
    }

    public LiveData<List<DataEntity>> getRoomData(){
        return mRepository.getData();
    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            textEditText = editable.toString();
        }
    };

    public String getTextEditText() {
        return textEditText;
    }

    public void setTextEditText(String textEditText) {
        this.textEditText = textEditText;
    }

    public void insertData(String data){
        mRepository.InsertData(data);
    }




    public String getTextHello() {
        return textHello;
    }

    public void setTextHello(String textHello) {
        this.textHello = textHello;
    }

    public String getTextTest() {
        return textTest;
    }

    public void setTextTest(String textTest) {
        this.textTest = textTest;
    }

    public LiveData<String> getTextInput() {
        return textInput;
    }

    public void setTextInput(String input){
        textInput.setValue(input);
    }
}
