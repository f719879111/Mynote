package com.mynote.server.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.tools.DateHelper;
import com.example.tools.MemoDB;
import com.example.tools.TypeConvert;
import com.mynote.bean.MemoInfo;
import com.mynote.server.MemoDao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// 实现MemoDao的方法
public class MemoIml implements MemoDao {

	private MemoDB memoDB=null;
	
	public MemoIml(Context context){
		memoDB=new MemoDB(context);	
	}
	//添加便签内容
	// true--添加成功，false--添加失败
	@Override
	public boolean addMemo(MemoInfo memoInfo) {
		SQLiteDatabase db=memoDB.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("content", memoInfo.getContent());
		values.put("create_time", memoInfo.getCreateTime());
		long line=db.insert("t_infos", null, values);
		db.close();
		return line>0;
	}
	// 修改便签信息
	// true--修改成功，false--修改失败
	@Override
	public boolean updateMemo(MemoInfo memoInfo,boolean isUpdateCreatetime) {
		SQLiteDatabase db=memoDB.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("content", memoInfo.getContent());
		if(isUpdateCreatetime)
			values.put("create_time", DateHelper.getDateTime(DateHelper.dateFormat2));
		int line=db.update("t_infos", values, "create_time=? or _id=?", new String[]{memoInfo.getCreateTime(),memoInfo.getId()+""});
		db.close();
		return line>0;
	}
	// 根据便签创建时间删除便签信息
	@Override
	public boolean deleteMemo(int id,String createTime) {
		SQLiteDatabase db=memoDB.getWritableDatabase();
		int line=db.delete("t_infos", "_id=? or create_time=?", new String[]{id+"",createTime});
		db.close();
		return line>0;
	}
	
	//  获取MemoInfo,cursor	游标,memoInfo对
	private MemoInfo getMemoInfo(Cursor cursor,MemoInfo memoInfo){
		if(memoInfo==null)
			memoInfo=new MemoInfo();
		memoInfo.setId(cursor.getInt(cursor.getColumnIndex("_id")));
		memoInfo.setContent(cursor.getString(cursor.getColumnIndex("content")));
		memoInfo.setCreateTime(cursor.getString(cursor.getColumnIndex("create_time")));
		return memoInfo;
	}
	// 根据便签编号指定查询便签信息

	public MemoInfo queryMemoById(int id) {
		SQLiteDatabase db=memoDB.getReadableDatabase();
		Cursor cursor=db.query("t_infos", null, "_id=?", new String[]{id+""}, null, null, "create_time desc");
		if(cursor==null)
			return null;
		MemoInfo memoInfo=new MemoInfo();
		while(cursor.moveToNext()){
			getMemoInfo(cursor, memoInfo);
		}
		cursor.close();
		db.close();
		return memoInfo;
	}
	// 根据便签的创建时间查询
	@Override
	public List<MemoInfo> queryMemoBytime(String createTime,List<MemoInfo> memoInfos) {
		SQLiteDatabase db=memoDB.getReadableDatabase();
		//1.使用这种query方法%号前不能加' ;
		Cursor cursor = db.query("t_infos",null, "create_time  like ? ",new String[] { "%" + createTime + "%" }, null, null, "create_time desc");
		if(cursor==null)
			return null;
		if(memoInfos==null)
			memoInfos=new ArrayList<MemoInfo>();
		while(cursor.moveToNext()){
			MemoInfo memoInfo=new MemoInfo();
			getMemoInfo(cursor, memoInfo);
			memoInfos.add(memoInfo);
		}
		cursor.close();
		db.close();
		return memoInfos;
	}
	// 查询全部的便签信息

	public List<MemoInfo> queryAllMemo(List<MemoInfo> memoInfos) {
		SQLiteDatabase db=memoDB.getReadableDatabase();
		Cursor cursor=db.query("t_infos", null, null,null, null, null, "create_time desc");
		if(cursor==null)
			return null;
		if(memoInfos==null)
			memoInfos=new ArrayList<MemoInfo>();
		while(cursor.moveToNext()){
			MemoInfo memoInfo=new MemoInfo();
			getMemoInfo(cursor, memoInfo);
			memoInfos.add(memoInfo);
		}
		cursor.close();
		db.close();
		return memoInfos;
	}
	// 根据多种条件查询便签 selection 查询的条件 selectionArgs 查询条件的参数
	@Override
	public MemoInfo queryMemoByMoreSelection(String selection, String...selectionArgs) {
		MemoInfo memoInfo=null;
		SQLiteDatabase db=memoDB.getReadableDatabase();
		Cursor cursor=db.query("t_infos", null, selection, selectionArgs, null, null, "create_time desc");
		if(cursor==null)
			return null;
		while(cursor.moveToNext()){
			memoInfo=getMemoInfo(cursor, memoInfo);
		}
		cursor.close();
		db.close();
		return memoInfo;
	}
}
