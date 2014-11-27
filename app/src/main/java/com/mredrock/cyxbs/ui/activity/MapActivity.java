package com.mredrock.cyxbs.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.impl.AppBarImpl;
import com.mredrock.cyxbs.ui.widget.swipebacklayout.app.SwipeBackActivity;

public class MapActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_map,R.string.title_activity_map);
    }

}
