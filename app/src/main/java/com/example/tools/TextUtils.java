package com.example.tools;

/**
 * 验证字符的合法性
 */
public class TextUtils {

	/**
	 * 自动补位，自动补齐成2位
	 * @param strNum
	 * @return
	 */
	public static String supplementZero(int num){
		String strNum=num+"";
		if(strNum.length()==1)
			return "0"+strNum;
		else
			return strNum;
	}
	
	/**
	 * 验证字符串是否为空
	 * @param str 需要判断的字符
	 * @return	true，该字符串为空；false，该字符串不为空
	 */
	public static boolean isEmpty(Object str){
		if(str==null || str=="")
			return true;
		String temp=str.toString().trim();
		if(temp.length()<=0)
			return true;
		return false;
	}
	
}
