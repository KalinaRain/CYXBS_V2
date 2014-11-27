package com.mredrock.cyxbs.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.impl.AppBarImpl;
import com.mredrock.cyxbs.ui.widget.swipebacklayout.app.SwipeBackActivity;

public class SettingActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_setting,R.string.title_activity_setting);
    }

}
