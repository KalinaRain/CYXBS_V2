package com.mredrock.cyxbs.ui.activity;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.impl.AppBarImpl;
import com.mredrock.cyxbs.util.AppManager;
import com.mredrock.cyxbs.util.LogUtils;

import static com.mredrock.cyxbs.util.LogUtils.LOGD;
import static com.mredrock.cyxbs.util.LogUtils.LOGV;

public class BaseToolBarActivity extends BaseActivity implements AppBarImpl {

    private static final String TAG = LogUtils.makeLogTag(MainActivity.class);
    private View contentView;
    private LinearLayout baseLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.anim_slide_in_from_right,R.anim.anim_slide_out_from_right);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tool_bar);
        baseLayout = (LinearLayout) findViewById(R.id.view_container);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_from_right,R.anim.anim_slide_out_from_right);
    }

    public void setContentLayout(int resId,int title) {
        configureToolbar(title);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(resId, null);
        if (baseLayout != null){
            if(contentView!=null){
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                baseLayout.addView(contentView,layoutParams);
            }
            // 添加Activity到堆栈
//            AppManager.getAppManager().addActivity(this);
//            LOGV("AppManager", "AppManager 添加actiivty！！" + this.getLocalClassName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void configureToolbar() {

    }

    @Override
    public void configureToolbar(int title) {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void setContentLayout(View view) {
        if (null != baseLayout) {
            baseLayout.addView(view);
        }
    }
}
