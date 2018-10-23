package com.architecture.wplib.ui;

import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public int getDrawableId(String name) {
        ApplicationInfo appInfo = getApplicationInfo();
        int resID = getResources().getIdentifier(name, "drawable", appInfo.packageName);
        return resID;
    }

    public Bitmap getBitmapByName(String name) {
        int resID = getDrawableId(name);
        return BitmapFactory.decodeResource(getResources(), resID);
    }

    public Drawable getDrawableByName(String name) {
        int resID = getDrawableId(name);
        return  getResources().getDrawable(resID);
    }

}
