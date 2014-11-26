package com.mredrock.cyxbs.io.request;

import android.app.Application;
import android.content.SharedPreferences;

import com.mredrock.cyxbs.Config;
import com.mredrock.cyxbs.model.Account;
import com.mredrock.cyxbs.util.JsonUtils;
import com.mredrock.cyxbs.util.LogUtils;
import com.mredrock.cyxbs.util.SpUtils;

import net.smalinuxer.spillover.frameDesign.Request;
import net.smalinuxer.spillover.frameDesign.RequestHandler;
import net.smalinuxer.spillover.frameDesign.SpillOver;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by David on 2014/11/4.
 */
public class ApplicationController extends Application{
    private HashMap<String, HashMap<String, Object>> activityState = new HashMap<String, HashMap<String, Object>>();
    public static RequestHandler mHandler;
    private static final String TAG = LogUtils.makeLogTag(ApplicationController.class);
    private Account mainAccount;

    public HashMap<String, Object> getActivityState(String key) {
        if(activityState.get(key)==null){
            activityState.put(key, new HashMap<String,Object>());
        }
        return activityState.get(key);
    }

    public void saveActivityState(String key,HashMap<String, Object> state) {
        this.activityState.put(key, state);
    }

    public void clearActivityState(){
        activityState.clear();
    }

    public Account getMainAccount() {
        SharedPreferences msp = SpUtils.getPreference(this);
        if(mainAccount == null){
            String mAccount = msp.getString(Config.LOCAL_SP_ACCOUNT,null);
            if(mAccount == null){
                mainAccount = null;
            }else{
                mainAccount = (Account) JsonUtils.json2Bean(mAccount,Account.class);
            }
        }
        return mainAccount;
    }

    public void setMainAccount(Account mainAccount) {
        this.mainAccount = mainAccount;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            mHandler = SpillOver.newRequestQueue(getApplicationContext());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void add2RequestQueue(Request<?> request){
        mHandler.add(request);
    }
}
