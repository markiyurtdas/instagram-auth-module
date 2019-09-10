package com.marki.instaaccess;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InstaAccess extends AppCompatActivity implements AuthenticationListener {
    private AuthenticationDialog authenticationDialog = null;

    ImageView btn_insta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insta);
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
        if (getResources().getString(R.string.client_id).compareTo("0")!=0){
            authenticationDialog = new AuthenticationDialog(this, this);
            authenticationDialog.setCancelable(true);
            authenticationDialog.show();
        }else {
            Toast.makeText(this, "Error!!! When loading Instagram Login page \nPlease get a client-id from Instagram Developer site", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    public void onTokenReceived(String auth_token) {
        Log.d("zxcLibraryInstaAccess", "onTokenReceived: " + auth_token);
        proccessIsOK(auth_token);
    }

    private void proccessIsOK(String access_token){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("insta_token",access_token);
        setResult(RESULT_OK,resultIntent);

        finish();
    }
}