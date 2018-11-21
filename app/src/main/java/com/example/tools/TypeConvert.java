package com.example.tools;

/**
 * 数据类型转换类
 */
public class TypeConvert {

	/**
	 * 将整数转换成boolean型
	 * @param value	一个整数
	 * @return	如果传入的值为0，返回false，其余的值统统返回true
	 */
	public static boolean intToBoolean(int value){
		return value==0 ? false:true;
	}
	
	/**
	 * 将boolean型转换成整数
	 * @param value	一个boolean型
	 * @return	如果传入的值为true，返回1，反之返回0
	 */
	public static int booleanToInt(boolean value){
		return value==true ? 1:0;
	}
	
}
