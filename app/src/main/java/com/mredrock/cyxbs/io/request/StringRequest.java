package com.mredrock.cyxbs.io.request;

/**
 * Created by David on 2014/11/2.
 */

import com.mredrock.cyxbs.util.LogUtils;

import net.smalinuxer.spillover.frameDesign.Request;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.mredrock.cyxbs.util.LogUtils.LOGD;
import static com.mredrock.cyxbs.util.LogUtils.LOGI;

/**
 * 无处理的StringRequest
 */
public class StringRequest extends Request<String> {

    private static final String TAG = LogUtils.makeLogTag(StringRequest.class);

    private Map<String,String> requestHeaders = null;

    private Map<String,String> requestParams = null;

    public StringRequest(String url,
                         Request.ResponseListener<String> listener) {
        super(url, listener);
    }

    public StringRequest(String url,
                         Request.ResponseListener<String> listener,
                         Map<String,String> requestParams
    ) {
        super(url, listener);
        this.requestParams = requestParams;
    }

    public StringRequest(String url,
                         Request.ResponseListener<String> listener,
                         Map<String,String> requestParams,
                         Map<String,String> requestHeaders
    ) {
        super(url, listener);
        this.requestHeaders = requestHeaders;
        this.requestParams = requestParams;
    }

    @Override
    public Map<String, String> getHeader() {
        return requestHeaders;
    }

    @Override
    public Map<String, String> getParam() {
        return requestParams;
    }

    @Override
    protected String handlerCallBack(byte[] arg0, String arg1) {
        try {
            String s = new String(arg1.getBytes("iso8859-1"),"UTF-8");
            LOGD(TAG,s);
            return s;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return arg1;
        }
//        return arg1;
    }

}