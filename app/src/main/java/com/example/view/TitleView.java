package com.example.view;

import com.mynote.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 标题栏
 */
public class TitleView extends LinearLayout {

	private ViewHolder mViewHolder;
	
	private Context context;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.context=context;
		
		mViewHolder = new ViewHolder();

		LayoutInflater.from(context).inflate(R.layout.title_bar, this);

		initView();

	}
	
	/**
	 * 初始化控件
	 */
	private void initView() {
		mViewHolder.ivCancel = (ImageView) findViewById(R.id.iv_cancel);
		mViewHolder.ivSubmit = (ImageView) findViewById(R.id.iv_submit);
		mViewHolder.tvTitle = (TextView) findViewById(R.id.tv_title);
	}

	/**设置标题栏显示文字*/
	public void setTitleText(String text){
		if(text==null || text.trim().equals("")) return;
		mViewHolder.tvTitle.setText(text);
	}
	
	/**设置左侧是否显示图表*/
	public void setLeftVisibility(int visibility){
		mViewHolder.ivCancel.setVisibility(visibility);
	}
	
	/**设置右侧是否显示图表*/
	public void setRightVisibility(int visibility){
		mViewHolder.ivSubmit.setVisibility(visibility);
	}
	
	/**设置左侧显示图表*/
	public void setLeftImage(int resId){
		mViewHolder.ivCancel.setImageResource(resId);
	}
	
	/**设置右侧显示图表*/
	public void setRightImage(int resId){
		mViewHolder.ivSubmit.setImageResource(resId);
	}
	
	/**为左侧返回按钮添加自定义点击事件*/
	public void setLeftImageViewListener(OnClickListener listener) {
		mViewHolder.ivCancel.setOnClickListener(listener);
	}
	
	/**为右侧提交按钮添加自定义点击事件*/
	public void setRightImageViewListener(OnClickListener listener) {
		mViewHolder.ivSubmit.setOnClickListener(listener);
	}

	class ViewHolder {
		private ImageView ivCancel, ivSubmit;
		private TextView tvTitle;
	}
}
