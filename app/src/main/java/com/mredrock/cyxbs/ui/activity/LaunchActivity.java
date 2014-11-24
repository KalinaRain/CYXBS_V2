package com.mredrock.cyxbs.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.mredrock.cyxbs.Config;
import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.util.LogUtils;
import com.mredrock.cyxbs.util.SpUtils;

import java.util.Timer;
import java.util.TimerTask;

import static com.mredrock.cyxbs.util.LogUtils.LOGD;

public class LaunchActivity extends BaseActivity {
    private static final String TAG = LogUtils.makeLogTag(LaunchActivity.class);

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readAccount();
    }

    /**
     * read account info from SharedPreferences
     */
    private void readAccount() {
        sp = SpUtils.getPreference(this);
        Boolean mFirst = sp.getBoolean(Config.LOCAL_SP_IS_GUID,true);
        if(mFirst){
            //LOGD(TAG,"TO-GUIDE");
            IntentTo(new Intent(LaunchActivity.this,GuideActivity.class));
        }else{
            String mAccount=sp.getString(Config.LOCAL_SP_ACCOUNT, "null");
            if(mAccount.equals("null")){
                Intent i=new Intent(LaunchActivity.this,LoginActivity.class);
                IntentTo(i);
            }else{
                IntentTo(new Intent(LaunchActivity.this,MainActivity.class));
            }
        }
    }

    public void IntentTo(Intent intent){
        if(intent!=null){
            startActivity(intent);
            finish();
        }
    }
}
