package com.dhvc.utils;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;

import com.dhvc.R;

public class WindowsUtils {

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void setStatusBarColor(Window window, Resources resources, int id){
        //After LOLLIPOP not translucent status bar
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Then call setStatusBarColor.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        window.setStatusBarColor(resources.getColor(R.color.black));
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//隐藏状态栏但不隐藏状态栏字体
//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏，并且不显示字体
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏文字颜色为暗色

        window.setStatusBarColor(id);
    }

}
