package com.mredrock.cyxbs.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.impl.AppBarImpl;
import com.mredrock.cyxbs.ui.widget.swipebacklayout.app.SwipeBackActivity;
import com.mredrock.cyxbs.util.LogUtils;

import static com.mredrock.cyxbs.util.LogUtils.LOGD;

public class AboutUsActivity extends SwipeBackActivity {

    private static final String TAG = LogUtils.makeLogTag(AboutUsActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_about_us,R.string.title_activity_about_us);
    }


}
