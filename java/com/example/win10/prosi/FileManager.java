package com.example.win10.prosi;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Base64;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by i15028 on 28/03/2018.
 */

public class FileManager {
    private String sharedPreFile = "com.example.labftis.templateandroid";
    private Context context;
    private SharedPreferences mPreferences;
    File dir;


    public FileManager(Context context){
        this.mPreferences=context.getSharedPreferences(sharedPreFile,Context.MODE_PRIVATE);
        this.context=context;
        dir=context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        System.out.println(dir.getAbsolutePath());
    }

    public Bitmap getBitmap(String title){

        return BitmapFactory.decodeFile(dir+"/"+title+".jpg");
    }

    public String convertThumbnailToBase64(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.JPEG,10,baos);
        byte[] b = baos.toByteArray();

        //System.out.println(Base64.encodeToString(b,Base64.DEFAULT));
        return Base64.encodeToString(b,Base64.DEFAULT);
    }

    public Bitmap getPreview(String title) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(dir+"/"+title+".jpg", bounds);
        if ((bounds.outWidth == -1) || (bounds.outHeight == -1))
            return null;
        int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
                : bounds.outWidth;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = originalSize / 128;

        return BitmapFactory.decodeFile(dir+"/"+title+".jpg", opts);
    }

    public String getActualImage(String title) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeFile(dir+"/"+title+".jpg");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    public ArrayList<ImageData> loadFromTxtToSend() {
        ArrayList<ImageData> res = new ArrayList<ImageData>();
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File in = new File(dir, "res.txt");
        if(in.exists()){
            Scanner sc = null;
            try {
                sc = new Scanner(in);
                while(sc.hasNextLine()){
                    String[] data = sc.nextLine().split(" ");
                    if(data.length<1){
                        break;
                    }
                    //System.out.println(data[0]);
                    String image = getActualImage(data[0]);
                    ImageData id ;
                    if(data.length==1){
                        id=new ImageData(image,data[0],"");
                    }
                    else{
                        id = new ImageData(image,data[0],data[1]);
                    }
                    res.add(id);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return res;

        }
        return null;


    }

    public ArrayList<ImageData> loadFromTxt() {
        ArrayList<ImageData> res = new ArrayList<ImageData>();
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File in = new File(dir, "res.txt");
        if(in.exists()){
            Scanner sc = null;
            try {
                sc = new Scanner(in);
                while(sc.hasNextLine()){
                    String[] data = sc.nextLine().split(" ");
                    if(data.length<1){
                        break;
                    }
                    //System.out.println(data[0]);
                    String image = convertThumbnailToBase64(getPreview(data[0]));
                    ImageData id ;
                    if(data.length==1){
                        id=new ImageData(image,data[0],"");
                    }
                    else{
                        id = new ImageData(image,data[0],data[1]);
                    }
                    res.add(id);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return res;

        }
        return null;


    }

    public ImageData loadData(){
        if(mPreferences.getInt("index",-1)==-1){
            return null;
        }

        String title = mPreferences.getString("title","");
        String notes = mPreferences.getString("notes","");
        String image ="";

        if(context.getClass().isInstance(MainActivityCamera.class)){
            image =convertThumbnailToBase64(getPreview(title));
        }
        else{
            image=convertThumbnailToBase64(BitmapFactory.decodeFile(dir+"/"+title+".jpg"));
        }

        if(title!="" ){
            if(notes==null){
                notes="";
            }
            return new ImageData(image,title,notes);
        }
        else{
            return null;
        }

    }

    public void saveData(ImageData id,int index){
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        String title="";
        String note="";
        if(id==null){

        }
        else{
            title = id.getTittle();
            note = id.getText();
        }
            preferencesEditor.putInt("index", index);
        preferencesEditor.putString("notes", note);
        preferencesEditor.putString("title", title);
        preferencesEditor.apply();
    }

    public String saveData(ImageData id,Uri uri,File photoFile){
        int val = mPreferences.getInt("index",-1);
        Uri photoURI=uri;
            File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if(!dir.exists()){
                dir.mkdirs();
            }

                String akhir = "";

                if(photoFile!=null) {

                    akhir =id.getTittle()+".jpg";
                    photoURI = FileProvider.getUriForFile(context,"com.prosi.android.fileprovider", photoFile);
                    this.saveFile(val,id);
                }
                return photoURI.toString()+" "+akhir;
    }

    public void saveFile(int index,ImageData id) {
        try {
            File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            BufferedWriter writer = null;
            File file = new File(dir, "res.txt");
            if(!file.exists()){
                FileWriter fw = new FileWriter(file);
                fw.append("");
                fw.flush();
                fw.close();
            }


            Scanner sc = new Scanner(file);
            System.out.println("Memasuki line"+index+" kosong!!");
            if (index != -1) {

                int line=0;
                ArrayList<String> isiText = new ArrayList<String>();
                String isi;
                while (sc.hasNextLine()){

                    isi=sc.nextLine()+"\n";
                    if(line==index){

                        isi=id.getTittle() + " " + id.getText() + "\n";
                    }
                    isiText.add(isi);
                    System.out.println("Memasuki line"+line+" kosong "+isi);
                    line++;
                }
                sc.close();

                writer = new BufferedWriter(new FileWriter(file));
                for(int i =0;i<isiText.size();i++){
                    System.out.println("Memasuki line"+i+" "+ isiText.get(i));
                    writer.append(isiText.get(i).toString());
                }
                writer.flush();
                writer.close();
            }
            else {
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(id.getTittle() + " " + id.getText() + "\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEMP" + timeStamp;

        if(!dir.exists()) {
            dir.mkdirs();
        }
        File image = File.createTempFile(imageFileName,".jpg",dir);

        return image;
    }



}
