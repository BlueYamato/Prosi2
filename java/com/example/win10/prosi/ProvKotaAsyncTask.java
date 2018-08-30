package com.example.win10.prosi;

import android.app.Fragment;
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


public class ProvKotaAsyncTask extends AsyncTask<String, Void, Void> {
    private final String BASE_URL = IPServerStatic.IP+"phpScriptProsi/provinsi.php";
    private final String provinsi_KEY_PARAM = "provinsi";
    public KotaProvListener main;
    private String[] out;
    private String tipe;
    private Gson gson;
    OkHttpClient client = new OkHttpClient();

    public ProvKotaAsyncTask(KotaProvListener main){
        this.main = main;
        this.gson = new Gson();
    }

    private boolean checkConnection(){
        ConnectivityManager connMgr;
        if (main instanceof FragmentGuru){
            connMgr = (ConnectivityManager) ((FragmentGuru)main).getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        else{
            connMgr = (ConnectivityManager) ((FragmentMurid)main).getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        }


        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        return netInfo!= null && netInfo.isConnected();
    }

    @Override
    protected void onPostExecute(Void unused) {
            if(tipe!="kosong"){
                if(out!=null)
                main.isiArray(out);
            }
            else{
                if(out!=null)
                main.isiProv(out);
            }
    }

    @Override
    protected Void doInBackground(String... itemData) {
        if(checkConnection()){
            //HttpURLConnection conn = null;
            int responseCode = 0;
            try{
                //URL requestURL = this.createURL();
                String input = itemData[0];
                tipe=input;
                RequestBody requestBody;
                if(input!="kosong"){


                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM).addFormDataPart(provinsi_KEY_PARAM,input)
                            .build();
                }
                else{
                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM).addFormDataPart("nothing",input)
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

                    if(input!="kosong"){
                        out = temp.split(",");


                        System.out.println("panjang string"+out.length);
                        for(int i =0;i<out.length;i++){
                            System.out.print(out[i]+" ");
                        }
                    }
                    else{
                        out = temp.split(",");
                    }
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
