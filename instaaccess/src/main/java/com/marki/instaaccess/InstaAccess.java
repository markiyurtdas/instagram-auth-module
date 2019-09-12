package com.marki.instaaccess;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.marki.instaaccess.model.InstaUser;
import com.marki.instaaccess.util.AuthenticationDialog;
import com.marki.instaaccess.util.AuthenticationListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class InstaAccess extends AppCompatActivity implements AuthenticationListener {

    ImageView btn_insta;
    private String token;


    InstaUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta);

        user = new InstaUser();
        btn_insta = findViewById(R.id.imageButton);
        btn_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInstaRequest();
            }
        });

        setTitle("Instagram");

        startInstaRequest();
    }

    private void startInstaRequest(){
        if (getResources().getString(R.string.client_id).compareTo("YOUR_KEY")!=0){
            AuthenticationDialog authenticationDialog = new AuthenticationDialog(this, this);
            authenticationDialog.setCancelable(true);
            authenticationDialog.show();
        }else {
            Toast.makeText(this, "Error!!! When loading Instagram Login page \nPlease get a client-id from Instagram Developer site", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onTokenReceived(String auth_token) {
        Log.d("zxcLibraryInstaAccess", "onTokenReceived: " + auth_token);
        token = auth_token;
        getUserInfoByAccessToken();

    }

    private void proccessIsOK(String access_token){
        Intent resultIntent = new Intent();



        resultIntent.putExtra("insta_token",access_token);
        resultIntent.putExtra("insta_username",user.getInstaUsername());
        resultIntent.putExtra("instta_profil_photo",user.getProfilPhotoUrl());
        resultIntent.putExtra("insta_full_name",user.getFullName());
        resultIntent.putStringArrayListExtra("insta_picture_list",user.getInstaPictures());
        setResult(RESULT_OK,resultIntent);

        finish();
    }













    private void getUserInfoByAccessToken() {
        new RequestInstagramAPI().execute();
        new RequestInstagramAPI2().execute();
    }


    //Get user info from Instagram with access token
    private class RequestInstagramAPI extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getResources().getString(R.string.get_user_info_url) + token);
            Log.d("tokenler","token:"+ token);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    JSONObject jsonData = jsonObject.getJSONObject("data");
                    if (jsonData.has("id")) {

                        user.setToken(jsonData.getString("id"));
                        user.setInstaUsername(jsonData.getString("username"));
                        user.setProfilPhotoUrl(jsonData.getString("profile_picture"));
                        user.setFullName(jsonData.getString("full_name"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast toast = Toast.makeText(getApplicationContext(),getResources().getString(R.string.login_failed),Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
    private class RequestInstagramAPI2 extends AsyncTask<Void, String, String> {

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(getResources().getString(R.string.get_user_media_url) + token);
            try {
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity httpEntity = response.getEntity();
                return EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e("response", jsonObject.toString());
                    JSONArray jsonDataList = jsonObject.getJSONArray("data");

                    if (jsonDataList.length()>=0) {

                        for(int n = 0; n < jsonDataList.length(); n++)
                        {
                            JSONObject object = jsonDataList.getJSONObject(n);
                            String type = object.getString("type");
                            Log.d("asdfg", "onPostExecute: " + type);
                            if (type.compareTo("image") ==0){
                                String thumbnail = object.getJSONObject("images")
                                        .getJSONObject("thumbnail")
                                        .getString("url");
                                String standartResolution = object.getJSONObject("images")
                                        .getJSONObject("standard_resolution")
                                        .getString("url");
                                user.addToList(thumbnail,standartResolution);
                            }else{
                                //İf object equals video then ignore
                            }


                        }//get images url
                        proccessIsOK(token);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Toast toast = Toast.makeText(getApplicationContext(),getResources().getString(R.string.login_failed),Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }


}