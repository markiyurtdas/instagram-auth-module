package com.marki.instaaccess.model;


import java.util.ArrayList;

public class InstaUser {


    private String token;
    private String instaUsername;

    private static String instaToken;
    private String profilPhotoUrl;
    private String fullName;
    private ArrayList<String> instaPictures = new ArrayList<>();

    public ArrayList<String> getInstaPictures() {
        return instaPictures;
    }



    public String getInstaUsername() {
        return instaUsername;
    }

    public void setInstaUsername(String instaUsername) {
        this.instaUsername = instaUsername;
    }

    public static String getInstaToken() {
        return instaToken;
    }

    public static void setInstaToken(String instaToken) {
        InstaUser.instaToken = instaToken;
    }

    public String getProfilPhotoUrl() {
        return profilPhotoUrl;
    }

    public void setProfilPhotoUrl(String profilPhotoUrl) {
        this.profilPhotoUrl = profilPhotoUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

//    public void addToInstagramList(String url){
//        instaPictures.add(url);
//    }
    public void removeInstagramList(int index){
        instaPictures.remove(index);
    }
    public void clearInstagramList(){
        instaPictures.clear();
    }
    public String getInstagramList(int index){
        return instaPictures.get(index);
    }
    public void addToList(String url1, String url2){
        this.instaPictures.add(url1);
        this.instaPictures.add(url2);
    }

}
