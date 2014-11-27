package com.mredrock.cyxbs.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.mredrock.cyxbs.io.request.ApplicationController;
import com.mredrock.cyxbs.util.LogUtils;

public class BaseActivity extends ActionBarActivity{
    private static final String TAG = LogUtils.makeLogTag(BaseActivity.class);

    public ApplicationController getApplicationControllor(){
        return (ApplicationController) getApplication();
    }



}
