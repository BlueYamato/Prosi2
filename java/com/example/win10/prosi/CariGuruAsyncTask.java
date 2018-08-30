package com.example.win10.prosi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CariGuruAsyncTask extends AsyncTask<String, Void, Void> {
    private final String BASE_URL = IPServerStatic.IP+"prosiCI/phpScriptProsi/cariGuru.php";
    private final String provinsi_KEY_PARAM = "nama";
    public PresenterListGuru main;
    private List<String> out;
    private String tipe;
    private Gson gson;
    OkHttpClient client = new OkHttpClient();

    public CariGuruAsyncTask(PresenterListGuru main){
        this.main = main;
        this.gson = new Gson();
        out = new ArrayList<String>();
    }

    private boolean checkConnection(){
        if ( main.activity != null) {
            ConnectivityManager connMgr = (ConnectivityManager)  main.activity.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected();
        }
        return false;

    }

    @Override
    protected void onPostExecute(Void unused) {
            if(tipe!="kosong"){
                if(out!=null) {
                    System.out.println("Akan masuk ke dalam kode");
                    main.loadString(out);
                }
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



                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM).addFormDataPart(provinsi_KEY_PARAM,input)
                            .build();

                Request request = new Request.Builder()
                        .url(BASE_URL)
                        .post(requestBody)
                        .build();
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){

                    responseCode=response.code();
                    String temp = response.body().string()+"";
                    System.out.println("Response body "+temp+" responseCode"+responseCode);


                        String[] res = temp.split(",");


                        //System.out.println("panjang string"+out.length);
                        for(int i =0;i<res.length;i++){
                            out.add(res[i]);
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
