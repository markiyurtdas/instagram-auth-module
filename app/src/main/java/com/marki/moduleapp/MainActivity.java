package com.marki.moduleapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.marki.instaaccess.InstaAccess;
import com.marki.instaaccess.model.InstaUser;


public class MainActivity extends AppCompatActivity {
    static private int INSTA_REQUEST_CODE=1001;
    static private int STORAGE_PERMISSION_CODE=1000;
    InstaUser instaUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            checkPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    STORAGE_PERMISSION_CODE);
        }else {
            startActivityForResult(new Intent(MainActivity.this, InstaAccess.class),INSTA_REQUEST_CODE);
        }





    }



    //For gettin token from instagramaccess module.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check if result is equals our request code
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INSTA_REQUEST_CODE) {
            // check Result is OK or Canceled
            if (resultCode == RESULT_OK) {
                //token from InstaAccess class. You can get info from instagram with this token.


                instaUser.setToken(data.getStringExtra("insta_token"));
                instaUser.setInstaUsername(data.getStringExtra("insta_username"));
                instaUser.setProfilPhotoUrl(data.getStringExtra("instta_profil_photo"));
                instaUser.setFullName(data.getStringExtra("insta_full_name"));
                String[] list = data.getStringArrayExtra("insta_picture_list");
                for (int i = 0; i<list.length;i++){
                    instaUser.addToList(list[i],list[i+1]);
                    i++;
                }


            }
            if (resultCode == RESULT_CANCELED) {
                //Show error Toast message if user canceled
                Toast.makeText(this, "User Canceled", Toast.LENGTH_SHORT).show();
            }

        }
    }


    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                MainActivity.this,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            MainActivity.this,
                            new String[] { permission },
                            requestCode);
        }
        else {
            Toast
                    .makeText(MainActivity.this,
                            "Permission already granted",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }



    //For request permissions.
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
                startActivityForResult(new Intent(MainActivity.this,InstaAccess.class),INSTA_REQUEST_CODE);
            }
            else {
                Toast.makeText(MainActivity.this,
                        "Storage Permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
