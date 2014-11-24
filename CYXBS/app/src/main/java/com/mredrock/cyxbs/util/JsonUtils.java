package com.mredrock.cyxbs.util;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2014/11/17.
 */
public class JsonUtils {
    private volatile static ObjectMapper mapper = null;
    public static ObjectMapper getMapperInstance(){
        if(mapper == null){
            synchronized (ObjectMapper.class){
                if(mapper == null){
                    mapper = new ObjectMapper();
//                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                }
            }
        }
        return mapper;
    }

    public static Object json2Bean(String json, Class<? extends Object> cls){
        Object object = null;
        try {
            object = getMapperInstance().readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;

    }

    public static Object input2Bean(InputStream is,Class<?> cls){
        Object obj = null;
        try{
            obj = getMapperInstance().readValue(is,cls);
        }catch(IOException e){
            e.printStackTrace();
        }
        return obj;
    }

    public static String bean2Json(Object obj){
        StringWriter writer = new StringWriter();
        JsonGenerator gen = null;
        try {
            gen = new JsonFactory().createJsonGenerator(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mapper.writeValue(gen, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json = writer.toString();
        if (gen != null) {
            try {
                gen.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return json;
    }

    public static String map2Json(Map<String,Object> map){//umimplement
        String json = null;
        return json;
    }

    public static String list2Json(List<Object> list){
        String json = null;
        return json;
    }
}
