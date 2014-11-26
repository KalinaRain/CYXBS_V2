package com.mredrock.cyxbs.io.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;

import com.mredrock.cyxbs.util.LogUtils;

import net.smalinuxer.spillover.frameDesign.Request;

import java.util.Map;

/**
 * Created by David on 2014/11/3.
 */
public class BitmapRequest extends Request {

    private Request<Bitmap> request;

    private static final String TAG = LogUtils.makeLogTag(BitmapRequest.class);

    public BitmapRequest(Request request){
        super(request.getUrl(), request.listener);
        this.request = request;
    }

    @Override
    public Map<String, String> getHeader() {
        return request.getHeader();
    }

    @Override
    public Map<String, String> getParam() {
        return request.getParam();
    }

    private Bitmap Bytes2Bimap(byte[] b,Bitmap.Config config){
        if(b.length!=0){
            BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
            decodeOptions.inPreferredConfig = config;
            return BitmapFactory.decodeByteArray(b, 0, b.length,decodeOptions);
        } else {
            return null;
        }
    }

    @Override
    protected Bitmap handlerCallBack(byte[] responseContent,
                                     String callBackdata) {
        return Bytes2Bimap(responseContent, Config.RGB_565);
    }

}

