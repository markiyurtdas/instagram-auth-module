package com.marki.instaaccess.util;


public interface AuthenticationListener {
    void onTokenReceived(String auth_token);
}
