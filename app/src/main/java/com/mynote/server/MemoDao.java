package com.mynote.server;

import java.util.List;

import com.mynote.bean.MemoInfo;

//便签的接口
public interface MemoDao {
    //添加便签内容
    // true--添加成功，false--添加失败
    public boolean addMemo(MemoInfo memoInfo);

    // 修改便签信息
    // true--修改成功，false--修改失败
    public boolean updateMemo(MemoInfo memoInfo, boolean isUpdateCreatetime);

    // 根据便签创建时间删除便签信息
    // @return	true--删除成功，false--删除失败
    public boolean deleteMemo(int id, String createTime);

    // 根据便签的创建时间查询
    public List<MemoInfo> queryMemoBytime(String createTime, List<MemoInfo> memoInfos);


	public MemoInfo queryMemoByMoreSelection(String selection,String[] selectionArgs);
}