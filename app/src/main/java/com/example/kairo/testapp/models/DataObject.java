package com.example.kairo.testapp.models;

/**
 * Created by kairon on 4/5/2016.
 */
public class DataObject {
    private String mVideo;
    private String mNumber;

    DataObject (String text1, String text2){
        mVideo = text1;
        mNumber = text2;
    }

    public String getmVideo() {
        return mVideo;
    }

    public void setmVideo(String mVideo) {
        this.mVideo = mVideo;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }
}