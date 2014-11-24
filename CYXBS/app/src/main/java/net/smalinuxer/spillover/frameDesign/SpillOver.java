package net.smalinuxer.spillover.frameDesign;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Build;

import net.smalinuxer.spillover.file.BasicCalculator;
import net.smalinuxer.spillover.file.BasicFileCache;
import net.smalinuxer.spillover.file.Cache;

public class SpillOver {

    public static final String DEFAULT_CACHE_DIR = "spillover";

    private static boolean isAlive = false;

    private static RequestHandler sHandler;

    public static RequestHandler newRequestQueue(Context context) throws IOException{

        synchronized(SpillOver.class){
            if(!isAlive){
                isAlive = true;
            } else {
                return sHandler;
            }
        }
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);
        Cache cache = new BasicFileCache(new BasicCalculator(),cacheDir);
        HttpHeap heap = null;
        if(Build.VERSION.SDK_INT >= 9 ){
            heap = new HttpLaunch();
        } else {
            throw new Error(new Throwable("SDK版本出现问题"));
        }
        RequestHandler handler = new RequestHandler(heap,cache);
        handler.init();
        sHandler = handler;

        return handler;
    }


}