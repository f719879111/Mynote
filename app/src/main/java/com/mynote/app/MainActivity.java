package com.mynote.app;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;

import com.example.tools.DateHelper;
import com.example.tools.TextUtils;
import com.mynote.adapter.InfoAdapter;
import com.mynote.bean.MemoInfo;
import com.mynote.server.dao.MemoIml;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnItemClickListener, OnItemLongClickListener {
    private static final String tag = MainActivity.class.getSimpleName();
    private GridView gvInfo;    // 网格布局
    private EditText etSearch;    // 搜索
    private InfoAdapter mAdapter = null;    // 适配器
    private ArrayList<MemoInfo> mDatas;    // 数据
    private static String mDate;
    private static Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mDatas = new ArrayList<MemoInfo>();
        mDate = DateHelper.getDateTime(DateHelper.dateFormat);
        gvInfo = (GridView) findViewById(R.id.gv_info);
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.setFocusable(false);
        gvInfo.setOnItemClickListener(this);
        etSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickDlg(MainActivity.this);
            }
        });
        findViewById(R.id.ll_add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        etSearch.setText(mDate);
        getDatas();
        super.onStart();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //MemoInfo info=(MemoInfo) mViewHolder.gvInfo.getItemAtPosition(position);
        return true;
    }

    /**
     * 显示日期弹出框
     */
    private void showDatePickDlg(final Context context) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String dateStr = year + "年" + TextUtils.supplementZero((monthOfYear + 1)) + "月" + TextUtils.supplementZero(dayOfMonth) + "日";
                mDate = dateStr;
                etSearch.setText(dateStr);
                etSearch.setFocusable(false);
                getDatas();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        // 点击其他部分不消失（false）
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    // GridView的点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MemoInfo memoInfo = (MemoInfo) gvInfo.getItemAtPosition(position);
        Intent intent = new Intent(MainActivity.this, BrowseMemoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("memoInfo", memoInfo);    // 便签信息集合
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //  获取数据
    private void getDatas() {
        if (mDatas != null)
            mDatas.clear();
        new MemoIml(getApplicationContext()).queryMemoBytime(mDate, mDatas);
        setAdapters(mDatas);
    }

    // 设置适配器
    private void setAdapters(List<MemoInfo> infos) {
        if (null == infos) {
            throw new NullPointerException("List<MemoInfo> infos 为 null");
        }
        if (null == mAdapter) {
            mAdapter = new InfoAdapter(infos, this);
            gvInfo.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}
