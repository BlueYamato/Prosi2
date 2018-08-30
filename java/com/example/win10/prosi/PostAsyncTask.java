package com.example.win10.prosi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;


import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PostAsyncTask extends AsyncTask<String, Void, Void> {
    private final String BASE_URL = IPServerStatic.IP+"prosiCI/phpScriptProsi/login.php";
    private final String USERNAME_KEY_PARAM = "username";
    private final String PASS_PARAM = "password";
    private final String NAMA_PARAM = "nama";
    private final String REQUEST_PARAM="tipe";
    private final String EMAIL_PARAM="email";
    private Context main;
    private Gson gson;
    OkHttpClient client = new OkHttpClient();

    public PostAsyncTask(Context main){
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
                RequestBody requestBody;
                if(input.length>=4){

                    requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM).addFormDataPart(USERNAME_KEY_PARAM,input[0])
                            .addFormDataPart(PASS_PARAM, input[1])
                            .addFormDataPart(NAMA_PARAM, input[2])
                            .addFormDataPart(REQUEST_PARAM, input[3])
                            .addFormDataPart(EMAIL_PARAM,input[4])
                            .addFormDataPart("kota",input[5])
                            .addFormDataPart("nohp",input[6])
                            .addFormDataPart("alamat",input[7])


                            .build();
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
                    try {
                        String temp = response.body().string() + "";


                        boolean status =(temp.contains(","));

                        if (status) {
                            System.out.println(temp);
                            JSONObject json = new JSONObject(temp);

                            //JSONObject json = arr.getJSONObject(0);

                            String tipe = json.getString("Status");

                            if(tipe.equals("0")) {

                                Murid m = gson.fromJson(temp, Murid.class);

                                Intent itn = new Intent(main, MainActivity.class);

                                itn.putExtra("murid", gson.toJson(m));
                                main.startActivity(itn);
                            }
                            else{
                                Guru g = gson.fromJson(temp, Guru.class);

                                Intent itn = new Intent(main, Main2Activity.class);

                                itn.putExtra("guru", gson.toJson(g));
                                main.startActivity(itn);
                            }

                        }
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
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
