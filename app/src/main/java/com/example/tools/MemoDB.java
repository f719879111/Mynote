package com.example.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

// 便笺App的数据存储
public class MemoDB extends SQLiteOpenHelper {

	/**数据库名称*/
	private static final String NAME="memo_db";

	private static final int VERSION=1;
	
	/**
	 * 数据库的构造方法，用来定义数据库的名称、数据库的结果集、数据库的版本
	 * @param context	上下文对象
	 */
	public MemoDB(Context context) {
		super(context, NAME, null, VERSION);
	}

	/**
	 * 数据库第一次创建调用的方法
	 * @param db	被创建的数据库
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="create table t_infos(_id integer not null primary key autoincrement, content text not null, "
				+ "create_time text)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
