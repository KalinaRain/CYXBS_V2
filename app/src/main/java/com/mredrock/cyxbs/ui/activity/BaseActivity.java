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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
