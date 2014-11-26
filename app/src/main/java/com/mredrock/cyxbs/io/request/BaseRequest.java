package com.mredrock.cyxbs.io.request;

import com.mredrock.cyxbs.util.LogUtils;

import net.smalinuxer.spillover.frameDesign.Request;

import java.util.Map;

/**
 * Created by David on 2014/11/14.
 */
public class BaseRequest extends Request {
    private static final String TAG = LogUtils.makeLogTag(BaseRequest.class);

    private Map<String,String> requestHeaders = null;

    private Map<String,String> requestParams = null;

    public BaseRequest(String url, ResponseListener listener) {
        super(url, listener);
    }

    @Override
    public Map<String, String> getHeader() {
        return null;
    }

    @Override
    public Map<String, String> getParam() {
        return null;
    }

    @Override
    protected Object handlerCallBack(byte[] responseContent, String callBackdata) {
        return callBackdata;
    }

}
