package com.mredrock.cyxbs.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mredrock.cyxbs.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by David on 2014/11/4.
 */
public class UIUtils {


    /**
     * ShortToast
     * @param ctx
     * @param content
     */
    public static void Toast(Context ctx,String content){
        Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * judge the app run background whether or not
     * @param context
     * @return
     */
    public static boolean isBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * dp2px
     *
     */
    public static int dip2px(Context ctx,float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     *	px2dp
     */
    public static int px2dip(Context ctx,float pxValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * get the screenWidth
     * @param ctx
     * @return
     */
    public static int getScreenWidth(Context ctx){
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * copyToClipboard
     * @param ctx
     * @param text
     */
    public static void copyToClipboard(Context ctx,String text){
        ClipboardManager cbm = (ClipboardManager) ctx.getSystemService(Activity.CLIPBOARD_SERVICE);
        cbm.setPrimaryClip(ClipData.newPlainText("redrock", text));
    }

    /**
     * Close the input
     * @param act
     */
    public static void closeInputMethod(Activity act){
        View view = act.getCurrentFocus();
        if(view!=null){
            ((InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * the get the Input state      为什么没用！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * @param ctx
     * @return
     */
    public static boolean isOnInputMethod(Activity act){
        return ((InputMethodManager)act.getSystemService(Context.INPUT_METHOD_SERVICE)).isActive();
        //return act.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
    }

    /**
     * clrear EmptyLine
     * @param content
     * @return
     */
    public static String clrearEmptyLine(String content){
        String result = content.replaceAll("(\r?\n(\\s*\r?\n)+)", "\r\n");
        if(result.startsWith("\r\n")){result = result.substring(2);}
        return result;
    }

    /**
     * open the Statusbar
     * @param ctx
     * @return
     */
    public static boolean openStatusbar(Context ctx){
        try {
            Object service = ctx.getSystemService ("statusbar");
            Class <?> statusBarManager = Class.forName
                    ("android.app.StatusBarManager");
            Method expand = statusBarManager.getMethod ("expand");
            expand.invoke (service);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Bitmap getViewBitmap(View v) {
        v.clearFocus();
        v.setPressed(false);
        boolean willNotCache = v.willNotCacheDrawing();
        v.setWillNotCacheDrawing(false);
        int color = v.getDrawingCacheBackgroundColor();
        v.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            v.destroyDrawingCache();
        }
        v.buildDrawingCache();
        Bitmap cacheBitmap = v.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);
        v.destroyDrawingCache();
        v.setWillNotCacheDrawing(willNotCache);
        v.setDrawingCacheBackgroundColor(color);
        return bitmap;
    }


    public static void saveBitmapToFile(Bitmap mBitmap,String bitpath,String bitName) throws IOException {
        File dir = new File(Config.dataFilePath);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }

        File f = new File(bitpath + bitName);
        if(!f.exists()){
            f.createNewFile();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * get the ScreenHeight
     * @param ctx
     * @return
     */
    public static int getScreenHeight(Context ctx){
        DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getScreenDensity(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager manager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            manager.getDefaultDisplay().getMetrics(dm);
            return dm.density;
        } catch (Exception ex) {
            return 1.0f;
        }

    }
}
