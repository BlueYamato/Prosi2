package com.example.win10.prosi;

import java.util.LinkedList;
import java.util.List;

public class PresenterListGuru {

    protected List<String> dataList;
    protected LesPrivatActivity activity;
    protected SQLiteHandler dbHandler;

    public PresenterListGuru(LesPrivatActivity activity){
        this.dataList = new LinkedList<>();
        this.activity = activity;
        this.dbHandler = new SQLiteHandler(activity);

    }

    public void runThread(){
        CariGuruAsyncTask cgat = new CariGuruAsyncTask(this);
        cgat.execute(activity.a);
    }
/*
    public List<Pengajar> loadData(){
        this.dataList = dbHandler.getAllPengajar(this.activity.a);
        return this.dataList;
    }
    */

    public void loadString(List<String> nama){
        this.dataList.addAll(nama);
        this.activity.updateData(dataList);
    }
}

