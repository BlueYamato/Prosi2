package com.example.win10.prosi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivityCamera extends Activity implements View.OnClickListener{
    Button btn;
    Button btnDaftar,btnBack;
    protected ListView lstImage;
    FileManager fm;
    ImageData id;
    ViewGroup.LayoutParams layoutParams;

    Button btnGalery,btnFromPhoto,btnClose;
    LinearLayout containerLayout;
    TextView tvMsg;
    ImageDataAdapter ida;
    PopupWindow popUpWindow;
    MainPresenter presenter;
    public static int REQUEST_Code=1;
    public static int REQUEST_Photo=2;
    public static int RESULT_LOAD_IMG=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_photo);

        containerLayout = new LinearLayout(this);

        popUpWindow = new PopupWindow(this);


        tvMsg = new TextView(this);
        btnGalery= new Button(this);

        btnGalery.setText("From galery");
        btnGalery.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                popUpWindow.dismiss();
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }

        });

        btnFromPhoto= new Button(this);
        btnFromPhoto.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                fm.saveData(null,-1);
                popUpWindow.dismiss();
                Intent itn = new Intent(btnFromPhoto.getContext(),MainActivityListImage.class);
                startActivityForResult(itn,REQUEST_Code);
            }

        });

        btnFromPhoto.setText("From photo");


        btnClose= new Button(this);
        btnClose.setOnClickListener(this);

        btnClose.setText("X");

        layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        containerLayout.addView(btnGalery, layoutParams);
        containerLayout.addView(btnFromPhoto, layoutParams);
        containerLayout.addView(btnClose, layoutParams);
        popUpWindow.setContentView(containerLayout);

        this.btn=findViewById(R.id.btn);
        this.btn.setOnClickListener(this);
        this.btnBack=findViewById(R.id.title_back);
        this.btnBack.setOnClickListener(this);
        this.btnDaftar=findViewById(R.id.finish);
        this.btnDaftar.setOnClickListener(this);
        this.fm = new FileManager(this);
        this.lstImage=findViewById(R.id.lst_image);

        this.presenter=new MainPresenter(this);

            ida = new ImageDataAdapter(this,presenter);
            this.lstImage.setAdapter(ida);

        lstImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageData selected= (ImageData) ida.getItem(i);
                fm.saveData(selected,i);
                startIntent();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            if (resultCode == Activity.RESULT_CANCELED && requestCode==REQUEST_Code) {
                Log.d("debugtestgo","RESuLTISOK");
                this.presenter.loadData();
                updateList();
            }
            else if(resultCode == Activity.RESULT_OK && requestCode==REQUEST_Photo){
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                Intent itn = new Intent(this,MainActivityListImage.class);
                itn.putExtra("bmap",fm.convertThumbnailToBase64(imageBitmap));
                startActivityForResult(itn,REQUEST_Code);
            }
            else if(resultCode == Activity.RESULT_OK && requestCode==RESULT_LOAD_IMG){
                try {
                    final Uri imageUri = data.getData();
                    String uridata = imageUri.toString();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    Intent itn = new Intent(this,MainActivityListImage.class);
                    itn.putExtra("bitmap",fm.convertThumbnailToBase64(selectedImage));
                    itn.putExtra("bmap",fm.convertThumbnailToBase64(selectedImage));

                    startActivityForResult(itn,REQUEST_Code);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }




            }



    }

    public void startIntent(){
        Intent itn = new Intent(this,MainActivityListImage.class);
        startActivityForResult(itn,REQUEST_Code);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btn.getId()){
            popUpWindow.showAtLocation(getWindow().getDecorView().getRootView(), Gravity.BOTTOM, 10, 10);

        }
        else if(v.getId()==this.btnClose.getId()){
            popUpWindow.dismiss();
        }
        else if(v.getId()==this.btnBack.getId()){
            Intent returnIntent = new Intent();
            popUpWindow.dismiss();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
       }
        else if(v.getId()==this.btnDaftar.getId()){
            Intent returnIntent = new Intent();
            popUpWindow.dismiss();
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

    }

    public ArrayList<ImageData> loadData(){
        ArrayList<ImageData> ld = new ArrayList<ImageData>();

        ld.add(id);
        return ld;
    }

    public void updateList(){
        this.ida.updateList();

    }
}
