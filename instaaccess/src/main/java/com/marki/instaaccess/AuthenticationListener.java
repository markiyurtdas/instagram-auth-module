package com.marki.instaaccess;


public interface AuthenticationListener {
    void onTokenReceived(String auth_token);
}
