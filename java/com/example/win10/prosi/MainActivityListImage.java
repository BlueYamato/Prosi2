package com.example.win10.prosi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class MainActivityListImage extends AppCompatActivity implements View.OnClickListener{
    Button btnSave,btnCancel;
    Button btn;
    ImageView ivPhoto;
    ImageData id;
    EditText et;
    EditText ettitle;
    FileManager fm;
    Uri photoURI;
    File photoFile = null;

    public static int REQUEST_IMAGE_CAPTURE =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlistimage);

        this.ivPhoto = findViewById(R.id.iv);
        this.btnSave=findViewById(R.id.btn_save);
        this.btnSave.setOnClickListener(this);
        this.btn=findViewById(R.id.btn);
        this.btnCancel=findViewById(R.id.btn_cancel);
        this.btnCancel.setOnClickListener(this);
        this.btn.setOnClickListener(this);
        this.et=findViewById(R.id.et_notes);
        this.ettitle=findViewById(R.id.et_judul);
        this.fm = new FileManager(this);

        this.ivPhoto.setOnClickListener(this);
        Intent intent = getIntent();
        String res = intent.getStringExtra("bitmap");
        if(res!=null){
            //this.photoURI=Uri.parse(intent.getExtras().getString("uri"));
            try {
                photoFile = fm.createImageFile();
            } catch (IOException ex) {


            }

            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this, "com.prosi.android.fileprovider", photoFile);
            }
            //System.out.println("Bitmap ada ditempatthx");

            byte[] b = Base64.decode(res,Base64.DEFAULT);

            Bitmap bm = BitmapFactory.decodeByteArray(b,0,b.length);

            this.ivPhoto.setImageBitmap(bm);

           // try {
               // photoFile = fm.createImageFile();
            //} catch (IOException ex) {


            //}

            //if (photoFile != null) {
            //    photoURI = FileProvider.getUriForFile(this,"com.prosi.android.fileprovider", photoFile);

            //}

        }
        else {
            id = fm.loadData();
            if (id != null) {
                this.et.setText(id.getText());
                this.ettitle.setText(id.getTittle());
                byte[] b = Base64.decode(id.getImage(), Base64.DEFAULT);
                Bitmap bm = BitmapFactory.decodeByteArray(b, 0, b.length);
                this.ivPhoto.setImageBitmap(bm);
                photoFile = new File(fm.dir, id.getTittle() + ".jpg");
                photoURI = FileProvider.getUriForFile(this, "com.prosi.android.fileprovider", photoFile);

            }
        }


    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK){
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),photoURI);
                this.ivPhoto.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==this.btn.getId()){
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager())!=null){

                try {
                    photoFile = fm.createImageFile();
                } catch (IOException ex) {


                }

                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(this,"com.prosi.android.fileprovider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    takePictureIntent.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION);

                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }


            }
        }
        else if(v.getId()==this.btnCancel.getId()){
            finish();
        }
        else if(v.getId()==this.btnSave.getId()){
            if(this.et.getText().toString()==""||this.ivPhoto.getDrawable()==null){
                Toast.makeText(this, "Please filled both image and title", Toast.LENGTH_SHORT).show();
            }

            else {
                Bitmap imageBitmap = ((BitmapDrawable) this.ivPhoto.getDrawable()).getBitmap();
                id = new ImageData(fm.convertThumbnailToBase64(imageBitmap), this.ettitle.getText().toString(), this.et.getText().toString());

                String[] res = fm.saveData(id, photoURI, photoFile).split(" ");
                photoURI = Uri.parse(res[0]);
                if (res[1] != "") {
                    photoFile.renameTo(new File(fm.dir, res[1]));
                }
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();


                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);

                finish();
            }

        }
        else if(v.getId()==this.ivPhoto.getId()){
            String sharedPreFile = "com.example.labftis.templateandroid";
            SharedPreferences mPreferences=getSharedPreferences(sharedPreFile,Context.MODE_PRIVATE);

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();

            preferencesEditor.putString("bitmap", fm.convertThumbnailToBase64(((BitmapDrawable) this.ivPhoto.getDrawable()).getBitmap()));

            preferencesEditor.apply();

            Intent itn = new Intent(this,MainActivityZoom.class);
            startActivity(itn);
        }
    }







}
