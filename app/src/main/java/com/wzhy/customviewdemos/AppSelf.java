package com.wzhy.customviewdemos;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * Created by techfit on 2017/5/17.
 */

public class AppSelf extends Application {

    private static AppSelf mApp;

    private static boolean isTest = true;

    public static AppSelf getApp() {
        return mApp;
    }

    public static Context getAppContext() {
        return mApp.getApplicationContext();
    }

    public static LayoutInflater getInflater() {
        return (LayoutInflater) getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static void showTip(String text) {
        Toast.makeText(getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showTestTip(String text) {
        if (!isTest) return;
        showTip(text);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
