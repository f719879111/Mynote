package com.mynote.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tools.TextUtils;
import com.example.view.TitleView;
import com.mynote.bean.MemoInfo;
import com.mynote.server.dao.MemoIml;

// 查看便笺详细信息的Activity
public class BrowseMemoActivity extends Activity {

    private com.example.view.TitleView titleBar;    // 标题栏
    private TextView tvDateAndTime;    // 创建时间
    private EditText etContent;    // 便笺内容
    private ImageView ivDelete;
    private MemoInfo memoInfo;    //  便签信息

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_memo);
        titleBar = (TitleView) findViewById(R.id.title_bar);
        tvDateAndTime = (TextView) findViewById(R.id.tv_dateAndTime);
        etContent = (EditText) findViewById(R.id.et_content);
        ivDelete = (ImageView) findViewById(R.id.iv_delete);
        initData();
        titleBar.setLeftVisibility(View.VISIBLE);
        titleBar.setRightVisibility(View.VISIBLE);
        titleBar.setTitleText("编辑便签");
        if (memoInfo != null) {
            if (memoInfo.getCreateTime() != null) {
                tvDateAndTime.setText(memoInfo.getCreateTime());
            }
            if (memoInfo.getContent() != null) {
                etContent.setText(memoInfo.getContent());
            }
        }
        addListener();
    }
    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                memoInfo=(MemoInfo) bundle.getSerializable("memoInfo");
            }
        }
    }
    // 添加事件
    private void addListener() {
        titleBar.setLeftImageViewListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.setRightImageViewListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString().trim();
                // 防止用户提交空数据到数据库中
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(BrowseMemoActivity.this, "请输入内容后在提交~", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 更新数据库
                memoInfo.setContent(content);
                new MemoIml(getApplicationContext()).updateMemo(memoInfo, true);
                finish();
            }
        });
        ivDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    // 删除
    private void delete() {
        Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("删除").setMessage("你确定要删除这条便签吗？");
        dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (new MemoIml(BrowseMemoActivity.this).deleteMemo(memoInfo.getId(), memoInfo.getCreateTime()))
                    finish();
                else Toast.makeText(BrowseMemoActivity.this, "删除失败~", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
