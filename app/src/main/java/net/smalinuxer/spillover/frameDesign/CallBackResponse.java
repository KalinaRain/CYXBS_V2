package net.smalinuxer.spillover.frameDesign;

import android.util.Log;

import com.mredrock.cyxbs.util.LogUtils;

import net.smalinuxer.spillover.file.Cache;

import java.io.IOException;
import java.util.concurrent.Executor;

import static com.mredrock.cyxbs.util.LogUtils.*;
import static com.mredrock.cyxbs.util.LogUtils.LOGE;
import static com.mredrock.cyxbs.util.LogUtils.LOGI;

public class CallBackResponse implements ResponseHandler{

    private static final String TAG= LogUtils.makeLogTag(CallBackResponse.class);

    private Executor mResponsePoster;

    private ResponseParse parse;

    private Cache mCache;

    private Object lock = new Object();

    public CallBackResponse(final android.os.Handler handler,ResponseParse parse, Cache cache) {
        mResponsePoster = new Executor() {
            @Override
            public void execute(Runnable command) {
                handler.post(command);
            }
        };
        this.parse = parse;
        this.mCache = cache;
    }


    @Deprecated
    public CallBackResponse(Executor executor) {
        mResponsePoster = executor;
    }

    @Override
    public void callErrorBack(final Request<?> request) {
        mResponsePoster.execute(new Runnable() {

            @Override
            public void run() {
                Cache.Entry entry = null;
                try {
                    LOGE(TAG,"CallBackResponse_callErrorBack:"+request.getUrl());
                    Log.e(TAG, "CallBackResponse_callErrorBack");
                    synchronized(lock){
                        entry = mCache.get(request.getUrl());
                    }

                    if(entry == null){
                        request.listener.callErrorBack(null,null);
                        return;
                    }

                    String data = parse.byteToEntity(entry.datas, entry.headers);
                    request.listener.callErrorBack(entry.datas,data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void callBack(final Request<?> request,final Response response) {
        mResponsePoster.execute(new Runnable() {

            @Override
            public void run() {
                LOGI(TAG, "CallBackResponse_callBack " + request.getUrl());
                request.listener.callBack(request.handlerCallBack(response.getDatas(),response.getCallBackdata()));
                LOGI(TAG,"CallBackResponse_callBack " +response.getCallBackdata()+"");
            }
        });
    }


}


