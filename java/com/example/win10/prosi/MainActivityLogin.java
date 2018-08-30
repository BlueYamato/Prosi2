package com.example.win10.prosi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//login
public class MainActivityLogin extends AppCompatActivity implements View.OnClickListener {
    Button btn,btnReg;
    EditText etUser,etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        this.btn=findViewById(R.id.btn_login);
        this.etUser=findViewById(R.id.et_username);
        this.etPass=findViewById(R.id.et_password);
        this.btn.setOnClickListener(this);
        this.btnReg=findViewById(R.id.halregister);
        this.btnReg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btn.getId()){
            String username=this.etUser.getText().toString();
            String password=this.etPass.getText().toString();
            if(username==""){
                Toast.makeText(this,"Username belum diisi",Toast.LENGTH_SHORT);
            }
            else if(password==""){
                Toast.makeText(this,"Password belum diisi",Toast.LENGTH_SHORT);
            }
            else{
                PostAsyncTask post = new PostAsyncTask(this);
                String val = username+","+password;
                post.execute(val);
                //login ke halaman
            }

        }
        if(view.getId()==btnReg.getId()){
            //panggilactivity daftar
            Intent itn = new Intent(this,MainActivityDaftar.class);
            startActivity(itn);

        }
    }
}
