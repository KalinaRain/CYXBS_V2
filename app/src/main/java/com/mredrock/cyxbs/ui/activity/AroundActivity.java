package com.mredrock.cyxbs.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.ui.impl.AppBarImpl;
import com.mredrock.cyxbs.ui.widget.swipebacklayout.app.SwipeBackActivity;

public class AroundActivity extends SwipeBackActivity implements AppBarImpl{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_around);
        configureToolbar();
    }

    @Override
    public void configureToolbar() {
        Toolbar aroundToolbar = (Toolbar) findViewById(R.id.toolbar_around);
        setSupportActionBar(aroundToolbar);
        getSupportActionBar().setTitle(R.string.title_activity_around);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
