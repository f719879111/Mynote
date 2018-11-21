package com.mynote.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tools.DateHelper;
import com.example.tools.TextUtils;
import com.example.view.TitleView;
import com.mynote.bean.MemoInfo;
import com.mynote.server.dao.MemoIml;

// 添加便签的Activity
public class AddMemoActivity extends Activity {
	private MemoInfo memoInfo;
	private com.example.view.TitleView titleBar;	// 标题栏
	private TextView tvDateAndTime;	// 创建时间
	private EditText etContent;	// 便笺内容
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_memo);
		memoInfo=new MemoInfo();
		titleBar=(TitleView) findViewById(R.id.title_bar);
		tvDateAndTime=(TextView) findViewById(R.id.tv_dateAndTime);
		etContent=(EditText) findViewById(R.id.et_content);
		findViewById(R.id.iv_delete).setVisibility(View.GONE);
		titleBar.setLeftVisibility(View.VISIBLE);
		titleBar.setRightVisibility(View.VISIBLE);
		titleBar.setTitleText("新建便签");
		tvDateAndTime.setText(DateHelper.getDateTime(DateHelper.dateFormat2));
		addListener();
	}
	//  添加事件
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
				String content=etContent.getText().toString().trim();
				// 防止用户提交空数据到数据库中
				if(TextUtils.isEmpty(content)){
					Toast.makeText(AddMemoActivity.this, "请输入内容后在提交~", Toast.LENGTH_SHORT).show();
					return;
				}
				// 写入到本地数据库
				saveMemo();
				finish();
			}
		});
	}

	boolean newFile=true;
	
	// 保存便笺到数据库 true,添加成功，false,添加失败
	protected boolean saveMemo() {
		boolean isSucceed = false;
		memoInfo.setContent(etContent.getText().toString().trim());
		if(newFile){
			memoInfo.setCreateTime(DateHelper.getDateTime(DateHelper.dateFormat2));	// 便笺创建时间
			isSucceed=new MemoIml(getApplicationContext()).addMemo(memoInfo);
			memoInfo=new MemoIml(getApplicationContext()).queryMemoByMoreSelection("create_time=?", memoInfo.getCreateTime());
			newFile=false;
		}else{
			isSucceed=new MemoIml(getApplicationContext()).updateMemo(memoInfo, true);
			memoInfo=new MemoIml(getApplicationContext()).queryMemoById(memoInfo.getId());
		}
		Toast.makeText(this, isSucceed ? "成功" : "失败", 1).show();
		return isSucceed;
	}
}
