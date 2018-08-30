package com.example.win10.prosi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int interval = 2000;
    protected Button cancelBtn;
    protected String a;
    protected int b;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);

        this.cancelBtn = findViewById(R.id.btn_cancel);
        this.cancelBtn.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        this.a = bundle.getString("pel");
        this.b = bundle.getInt("per");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(LoadActivity.this, LesPrivatActivity.class);
                Bundle newBundle = new Bundle();
                newBundle.putString("pel2",a);
                newBundle.putInt("per2",b);
                intent.putExtras(newBundle);
                startActivity(intent);
                
                this.finish();
            }

            private void finish() {
            }
        },interval
        );
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.cancelBtn.getId()){
            Intent intent = new Intent(this,FiturPrivatActivity.class);
            startActivity(intent);
        }
    }
}
