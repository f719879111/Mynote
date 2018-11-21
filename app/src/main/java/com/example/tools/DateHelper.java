package com.example.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

// 日期帮助类
public class DateHelper {
	
	/**yyyy年MM月dd日*/
	public static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy年MM月dd日");
	
	/**yyyy-MM*/
	public static final SimpleDateFormat dateFormat1=new SimpleDateFormat("yyyy-MM");
	
	/**yyyy年MM月dd日 HH:mm:ss*/
	public static final SimpleDateFormat dateFormat2=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	
	/**HH:mm*/
	public static final SimpleDateFormat dateFormat3=new SimpleDateFormat("HH:mm");
	
	/**yyyy年MM月dd日 HH:mm*/
	public static final SimpleDateFormat dateFormat4=new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	
	/**
	 * 返回date对象
	 * @param str	日期时间字符串
	 * @param dateFormat	相对应的日期格式化对象
	 * @return 返回date对象
	 */
	public static Date getDate(String str,SimpleDateFormat dateFormat){
		Date date=null;
		try {
			date=dateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取日期时间
	 * @return
	 */
	public static String getDateTime(SimpleDateFormat dateFormat){
		return dateFormat.format(new Date());
	}
	
	/**
	 * 获取日期 
	 * @param isShowDay 是否显示天,true显示yyyy-MM-dd ,false yyyy-MM
	 * @return
	 */
	public static String getDate(boolean isShowDay){
		if(isShowDay)
			return dateFormat.format(new Date());
		else
			return dateFormat1.format(new Date());
	}
	
	/**
	 * 获得毫秒数
	 * @param str	时间
	 * @param dateFormat	日期格式化
	 * @return 毫秒
	 */
	public static long getDateMills(String str,SimpleDateFormat dateFormat){
    	Date date=null;
    	try {
    		date=dateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Log.i("getDateMills", ""+date);
    	return null==date ? 0:date.getTime();
    }
	
}
