package com.example.win10.prosi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PostAsyncImageTask extends AsyncTask<String, Void, Void> {
    private final String BASE_URL = IPServerStatic.IP+"prosiCI/phpScriptProsi/registerguru.php";
    private final String USERNAME_KEY_PARAM = "username";
    private final String PASS_PARAM = "password";
    private final String NAMA_PARAM = "nama";
    private final String KOTA_PARAM="id_kota";
    private final String PENDIDIKAN_PARAM="pendidikan";
    private final String AJAR_PARAM="mengajar";
    private final String ALAMAT_PARAM="alamat";
    private final String EMAIL_PARAM="email";
    private final String TANGGAL_PARAM="tanggal";

    private Context main;
    private Gson gson;
    OkHttpClient client = new OkHttpClient();

    public PostAsyncImageTask(Context main){
        this.main = main;
        this.gson = new Gson();
    }

    private boolean checkConnection(){
        ConnectivityManager connMgr = (ConnectivityManager) main.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        return netInfo!= null && netInfo.isConnected();
    }

    @Override
    protected Void doInBackground(String... itemData) {
        if(checkConnection()){
            //HttpURLConnection conn = null;
            int responseCode = 0;
            try{
                //URL requestURL = this.createURL();
                String[] input = itemData[0].split(",");
                String image = itemData[1];
                String pel = itemData[2];
                RequestBody requestBody;
                if(input.length>=4){
                   // System.out.println(pel);

                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM).addFormDataPart(USERNAME_KEY_PARAM,input[0])
                            .addFormDataPart(PASS_PARAM, input[1])
                            .addFormDataPart(NAMA_PARAM, input[2])
                            .addFormDataPart(KOTA_PARAM, input[3])
                            .addFormDataPart(PENDIDIKAN_PARAM, input[4])
                            .addFormDataPart(AJAR_PARAM, input[5])
                            .addFormDataPart(TANGGAL_PARAM, input[6])
                            .addFormDataPart(EMAIL_PARAM, input[7])
                            .addFormDataPart(ALAMAT_PARAM, input[8])
                            .addFormDataPart("nohp", input[9])
                            //.addFormDataPart("bitmap", image[0])
                            //.addFormDataPart("judul", image[1])
                            //.addFormDataPart("deskripsi", image[2])
                            .addFormDataPart("sertif",image)
                            .addFormDataPart("pel",pel)

                            //.addFormDataPart(EMAIL_PARAM,input[4])
                            .build();
                    //System.out.println(image[0]);
                }
                else{
                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM).addFormDataPart(USERNAME_KEY_PARAM,input[0])
                            .addFormDataPart(PASS_PARAM, input[1])
                            .build();
                }
                Request request = new Request.Builder()
                        .url(BASE_URL)
                        .post(requestBody)
                        .build();
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){

                    responseCode=response.code();
                    String temp = response.body().string()+"";
                    System.out.println("Response body "+temp+" responseCode"+responseCode);

                    if(temp.contains(",")){
                        String[] out = temp.split(",");
                        System.out.println("panjang string"+out.length);
                        for(int i =0;i<out.length;i++){
                            System.out.print(out[i]+" ");
                        }
                        System.out.println();
                        if(out[1].length()>0){
                            //System.out.println("pengajar");
                        }
                        else{

                        }
                    }
                }
                else{
                    //String temp = response.body().string()+"";

                    //System.out.println("Response body "+temp+" responseCode"+responseCode);

                }


                // responseCode = conn.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                //if (conn != null){
                //conn.disconnect();
                //}
            }
            if(responseCode != 200){
                System.out.println(responseCode + " error");
            }
        }
        return null;
    }

}
