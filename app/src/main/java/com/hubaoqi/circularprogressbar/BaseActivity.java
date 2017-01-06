package com.hubaoqi.circularprogressbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 基类
 * 
 * @author 胡宝齐
 * 
 */
@SuppressWarnings("deprecation")
public abstract class BaseActivity extends Activity
{
    
    private Context mContext;
    
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    
    /**
     * 屏幕密度
     */
    public static float screenDensity;
    
    private DisplayMetrics dm;
    
    private WindowManager manager;
    
    SQLiteDatabase db;
    

    @Override
    protected void onStart()
    {
        super.onStart();
    }
    
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mContext = BaseActivity.this;
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        manager = this.getWindowManager();
        dm = getResources().getDisplayMetrics();
        screenDensity = dm.density;
        screenWidth = manager.getDefaultDisplay().getWidth();
        screenHeight = manager.getDefaultDisplay().getHeight();
        

        initHolder();
        initLayoutParams();
        initData();
        bindListener();
        
    }
    


    /**
     * 通过listView的item项得到listView的高
     * 
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            return;
        }
        
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
     
    /**
     * 设置布局文件
     * 
     * @return
     */
    public abstract int getLayoutId();
    
    /**
     * 初始化控件
     */
    public abstract void initHolder();
    
    /**
     * 初始化布局
     */
    public abstract void initLayoutParams();
    
    /**
     * 初始化数据
     */
    public abstract void initData();
    
    /**
     * 初始化监听
     */
    public abstract void bindListener();
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    
    /**
     * 设置界面title（使用此方法，界面布局文件必须导入title布局文件）
     * 
     * @param showleft  左面按钮是否显示
     * @param showmiddle 中间标题是否显示
     * @param showright  右面按钮是否显示
     * @param leftResId  左面按钮资源
     * @param middleContent  中间标题内容
     * @param rightResId 右面按钮资源
     */

    /**
     * 打开acitvity
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, 0);

    }

    public void openActivityForResult(Class<?> pClass, int requestCode) {
        openActivity(pClass, null, requestCode);
    }
    
    /**
     * 更具类打开acitvity,并携带参数
     */
    public void openActivity(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        if (requestCode == 0) {
        	startActivity(intent);
        } else {
             startActivityForResult(intent, requestCode);
        }
    }
}
