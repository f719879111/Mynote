package com.mynote.bean;

import java.io.Serializable;

/**
 * 便签实体类
 */
public class MemoInfo implements Serializable {
    private int id; // 记录编号
    private String content; // 内容
    private String createTime; // 创建时间

    public MemoInfo() {

    }

    public MemoInfo(int id, String content, String createTime) {
        super();
        this.id = id;
        this.content = content;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
