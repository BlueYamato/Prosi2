package com.example.win10.prosi;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivityZoom extends AppCompatActivity implements View.OnTouchListener,View.OnClickListener{
    ImageView ivCanvas;
    Matrix matrix;
    private Canvas mCanvas;
    private Paint strokePaint;
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mScaleGestureDetector;
    private String sharedPreFile = "com.example.labftis.templateandroid";
    private SharedPreferences mPreferences;


    Button btn;
    Bitmap mBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mPreferences=getSharedPreferences(sharedPreFile,Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_zoom);


        this.ivCanvas=this.findViewById(R.id.iv_canvas);
        this.btn=this.findViewById(R.id.btn_reset);

        this.btn.setOnClickListener(this);
        this.ivCanvas.setOnTouchListener(this);


        this.strokePaint = new Paint();

        this.ivCanvas.setScaleType(ImageView.ScaleType.MATRIX);
        this.mGestureDetector=new GestureDetector(this,new MyCustomGestureListener());
        this.mScaleGestureDetector=new ScaleGestureDetector(this,new MyCustomScaleListener());

        this.ivCanvas.post(new ThreadedActivity(this));



    }
    @Override
    public void onClick(View view) {

        if(view.getId()==this.btn.getId()){
                finish();

        }

    }

    public void initiateCanvas(){
        this.mBitmap = Bitmap.createBitmap(this.ivCanvas.getWidth(), this.ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);
        String bm = mPreferences.getString("bitmap","");
        byte [] encodeByte= Base64.decode(bm,Base64.DEFAULT);
        Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);


        this.ivCanvas.setImageBitmap(bitmap);
        this.mCanvas = new Canvas(mBitmap);
        mCanvas.drawBitmap(bitmap,0,0,strokePaint);
        matrix = new Matrix();
        ivCanvas.setImageMatrix(matrix);

        //resetCanvas
        this.ivCanvas.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


            mGestureDetector.onTouchEvent(motionEvent);
            mScaleGestureDetector.onTouchEvent(motionEvent);
            return true;


    }

    private class MyCustomScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        float lastFocusX,lastFocusY;

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //scale
            Matrix transformationMatrix = new Matrix();
            float focusX = detector.getFocusX();
            float focusY = detector.getFocusY();


            transformationMatrix.postTranslate(-focusX, -focusY);

            transformationMatrix.postScale(detector.getScaleFactor(), detector.getScaleFactor());


            float focusShiftX = focusX - lastFocusX;
            float focusShiftY = focusY - lastFocusY;
            transformationMatrix.postTranslate(focusX + focusShiftX, focusY + focusShiftY);
            matrix.postConcat(transformationMatrix);
            lastFocusX = focusX;
            lastFocusY = focusY;


            ivCanvas.setImageMatrix(matrix);
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            //set midpoint
            lastFocusX = detector.getFocusX();
            lastFocusY = detector.getFocusY();
            Log.d("test","on scale begin");
            return true;
        }
    }

    private class MyCustomGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //translate
            matrix.postTranslate(-distanceX, -distanceY);
            ivCanvas.setImageMatrix(matrix);
            return true;
        }
    }
}