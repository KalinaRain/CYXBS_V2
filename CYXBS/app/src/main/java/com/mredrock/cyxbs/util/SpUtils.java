package com.mredrock.cyxbs.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.mredrock.cyxbs.Config;

/**
 * Created by David on 2014/11/4.
 */
public class SpUtils {
    /**
     * 获取SharedPreferences
     * @param ctx
     * @return SharedPreferences
     */
    public static SharedPreferences getPreference(Context ctx) {
        return ctx.getSharedPreferences(Config.LOCAL_SP, Activity.MODE_PRIVATE);
    }
}
