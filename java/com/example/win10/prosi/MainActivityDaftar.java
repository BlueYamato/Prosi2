package com.example.win10.prosi;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//signup
public class MainActivityDaftar extends AppCompatActivity implements FragmentListener,View.OnClickListener {
    public FragmentMurid fragment1;
    public FragmentGuru fragment2;
    public FragmentSuccess fragment3;
    public FragmentGuruPilihPel fragment4;
    Button halLogin,halRegister;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_signup);
        this.fragment1 = FragmentMurid.newInstance("New Fragment 1");
        this.fragment2 = FragmentGuru.newInstance("New Fragment 2");
        this.fragment4 = FragmentGuruPilihPel.newInstance("New Fragment 2");
        this.fragment3 = FragmentSuccess.newInstance("New Fragment 2");

        this.fragmentManager = this.getSupportFragmentManager();
        this.halLogin=findViewById(R.id.hallogin);
        this.halLogin.setOnClickListener(this);


        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        this.changePage(1);
    }

    public void startIntent(){
        Intent itn = new Intent(this,MainActivityCamera.class);
        startActivityForResult(itn,1);
    }

    @Override
    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            if(this.fragment1.isAdded()){
                ft.show(this.fragment1);
            }
            else{
                ft.add(R.id.fragment_container, this.fragment1);
            }

            if(this.fragment2.isAdded()){
                ft.hide(this.fragment2);
            }
            if(this.fragment3.isAdded()){
                ft.hide(this.fragment3);
            }
            if(this.fragment4.isAdded()){
                ft.hide(this.fragment4);
            }
        }
        else if(page==2){
            if(this.fragment2.isAdded()){
                ft.show(fragment2);
            }
            else {
                ft.add(R.id.fragment_container, this.fragment2).addToBackStack(null);
            }
            if(this.fragment1.isAdded()){
                ft.hide(this.fragment1);
            }
            if(this.fragment3.isAdded()){
                ft.hide(this.fragment3);
            }
            if(this.fragment4.isAdded()){
                ft.hide(this.fragment4);
            }
        }
        else if(page==3){
            if(this.fragment4.isAdded()){
                ft.show(fragment4);
            }
            else {
                ft.add(R.id.fragment_container, this.fragment4).addToBackStack(null);
            }
            fragment4.setText(fragment2.mengajar.getSelectedItem().toString());

            if(this.fragment1.isAdded()){
                ft.hide(this.fragment1);
            }
            if(this.fragment2.isAdded()){
                ft.hide(this.fragment2);
            }
            if(this.fragment3.isAdded()){
                ft.hide(this.fragment3);
            }
        }
        else if(page==4){
            if(this.fragment3.isAdded()){
                ft.show(fragment3);
            }
            else {
                ft.add(R.id.fragment_container, this.fragment3).addToBackStack(null);
            }
            if(this.fragment1.isAdded()){
                ft.hide(this.fragment1);
            }
            if(this.fragment2.isAdded()){
                ft.hide(this.fragment2);
            }
            if(this.fragment4.isAdded()){
                ft.hide(this.fragment4);
            }
        }
        ft.commit();
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==this.halLogin.getId()){
            Intent itn = new Intent(this,MainActivityLogin.class);
            startActivity(itn);
            finish();

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_CANCELED && requestCode==1) {
            System.out.println("BLAAAAAARG");
            changePage(3);
        }
        else if(resultCode == Activity.RESULT_OK && requestCode==1){
            System.out.println("BLEEEEERG");
            FileManager fm = new FileManager(this);
            ArrayList<ImageData> im= fm.loadFromTxtToSend();
            PostAsyncImageTask pait = new PostAsyncImageTask(this);
            //JSONArray jArray = new JSONArray();
            String res="";
            for(int i = 0;i<im.size();i++){
                Gson gson = new Gson();
                String json = gson.toJson(im.get(i));
                if(i==0){
                    res+=json;
                }
                else{
                    res+="|thisspace|"+json;

                }
                //jArray.put(json);
            }

            //System.out.println(fragment4.getPel());

            pait.execute(this.fragment2.getData(),res,fragment4.getPel());
            changePage(4);
        }



    }

    public void tutup(){
        finish();
    }
}
