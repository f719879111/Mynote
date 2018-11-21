package com.example.tools;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import android.annotation.SuppressLint;
import android.view.WindowManager.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * 屏幕工具类
 */
@SuppressLint("NewApi")
public class Screen {

	public static DisplayMetrics getDisplayMetrics(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
	}
	
	/**
	 * px转换dp
	 * @param values
	 * @param density
	 * @return
	 */
	public static int px2dp(int values,float density){
		return (int) (values / density);
	}
	
	/**
     * 获取设备屏幕的宽
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context){
    	DisplayMetrics dm=getDisplayMetrics(context);
    	return px2dp(dm.widthPixels, dm.density);
    }

    /**获取屏幕的高*/
    public static int getWindowHeight(Context context){
    	DisplayMetrics dm=getDisplayMetrics(context);
    	return px2dp(dm.heightPixels, dm.density);
    }
}
