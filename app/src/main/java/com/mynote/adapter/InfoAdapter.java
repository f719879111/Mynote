package com.mynote.adapter;

import java.util.List;

import com.mynote.bean.MemoInfo;
import com.mynote.app.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

// 便签列表适配器
public class InfoAdapter extends BaseAdapter {

	private List<MemoInfo> mDatas;
	private Context mContext;
	//private static String curDate;

	public InfoAdapter(List<MemoInfo> datas, Context context) {
		this.mDatas = datas;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView, parent, R.layout.grid_item_1, position);
		TextView tvContent = holder.getView(R.id.tv_content);
		TextView tvDate = holder.getView(R.id.tv_date);
		MemoInfo info = mDatas.get(position);
		// 设置便签内容
		tvContent.setText(info.getContent());
		tvDate.setText(info.getCreateTime());
		return holder.getConvertView();
	}

}
