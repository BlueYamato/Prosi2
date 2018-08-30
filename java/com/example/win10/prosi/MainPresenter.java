package com.example.win10.prosi;

import java.util.ArrayList;


/**
 * Created by i15028 on 08/02/2018.
 */

public class MainPresenter {




    private ArrayList<ImageData> idl;
    protected MainActivityCamera ui;

    public MainPresenter(MainActivityCamera ui) {
        this.ui = ui;
        this.loadData();
    }

    public void loadData(){

        this.idl = ui.fm.loadFromTxt();
        if(idl==null){
            this.idl=new ArrayList<ImageData>();
        }
    }

    public ArrayList<ImageData> getIdl() {
        return idl;
    }

    public void deleteList(int i){
        this.idl.remove(i);
        this.ui.updateList();
    }

    public void addImageData(String image, String title,String text){

        this.idl.add(new ImageData(image, title,text));
        this.ui.updateList();
    }

    public void editImageData(ImageData data,int i){
        idl.set(i,data);
    }

    public ImageData returnImageData(int i){
        return idl.get(i);
    }



}
