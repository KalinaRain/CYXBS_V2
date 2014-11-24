package com.mredrock.cyxbs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 2014/10/30.
 */
public class Config {

    // Is this an internal dogfood build?
    public static final boolean IS_DOGFOOD_BUILD = true;

    /**
     * sharepreference name
     */
    public final static  String LOCAL_SP = "redrock";
    public final static  String LOCAL_SP_ACCOUNT = "account";
    public final static  String LOCAL_SP_ACCOUNTS = "accounts";
    public final static  String LOCAL_SP_IS_GUID = "guid";


    public final static String schedulePicALLName = "scheduleAllPic.png";
    public final static String schedulePicSingleName = "scheduleSinglePic.png";
    public final static String dataFilePath = android.os.Environment.getExternalStorageDirectory() +"/"+"Android/data/com.mredrock.cyxbs/";
    public final static String updataFilePath = android.os.Environment.getExternalStorageDirectory() +"/"+"download/";
    public final static String updataFilename = "com.mredrock.cyxbs.apk";

    public final static String website = "http://hongyan.cqupt.edu.cn/";

    /**
     * the code of info returned by serve
     */
    public final static  int STATUS_SUCCESS = 200;
    public final static  int STATUS_PARAMS_INVALID = -801;
    public final static  int STATUS_AUTHENTICATION_ERROR = 201;
    public final static  int STATUS_STUDENT_ID_ERROR = -100;

    /**
     * info of api
     */
    public final static  String WEB_NAME_KEY = "API_APP";
    public final static  String WEB_NAME_VALUE = "android";
    public final static  String WEB_TOKEN_KEY = "API_TOKEN";
    public final static  String WEB_TOKEN_VALUE = "0zLUZA0j+OL77OsjXC0ulOz50KaI6yANZtkOk2vQIDg=";

    /**
     * header
     * @return
     */
    public static Map<String,String> getHeader(){
       Map<String,String> header = new HashMap<String,String>();
        header.put(WEB_NAME_KEY,WEB_NAME_VALUE);
        header.put(WEB_TOKEN_KEY,WEB_TOKEN_VALUE);
        return header;
    }

    /**
     * API url
     */

    public final static String API_PERSON_SCHEDULE = "http://hongyan.cqupt.edu.cn/api/kebiao";
    public final static String API_ROOM_SCHEDULE = "http://hongyan.cqupt.edu.cn/api/roomkebiao";
    public final static String API_VERIFY = "http://hongyan.cqupt.edu.cn/api/verify";
    public final static String API_EMPTYROOM = "http://hongyan.cqupt.edu.cn/api/roomEmpty";
    public final static String API_EXAM = "http://hongyan.cqupt.edu.cn/api/examSchedule";
    public final static String API_REEXAM = "http://hongyan.cqupt.edu.cn/api/examReexam";
    public final static String API_SCORE = "http://hongyan.cqupt.edu.cn/api/examGrade";

    //教务新闻 m1
    public final static String API_JWNEWSLIST = "http://hongyan.cqupt.edu.cn/api/jwNewsList";
    public final static String API_JWNEWSCONTENT = "http://hongyan.cqupt.edu.cn/api/jwNewsContent";
    //校务公告 m2
    public final static String API_XWGGNEWSLIST = "http://hongyan.cqupt.edu.cn/api//xwggNewsList";
    public final static String API_XWGGNEWSCONTENT = "http://hongyan.cqupt.edu.cn/api/xwggNewsContent";
    //重邮新闻 m3
    public final static String API_CYXWNEWSLIST = "http://hongyan.cqupt.edu.cn/api/cyxwNewsList";
    public final static String API_CYXWNEWSCONTENT = "http://hongyan.cqupt.edu.cn/api/cyxwNewsContent";
    //活动通告 m2
    public final static String API_HDTGNEWSLIST = "http://hongyan.cqupt.edu.cn/api/hdtgNewsList";
    public final static String API_HDTGNEWSCONTENT = "http://hongyan.cqupt.edu.cn/api/hdtgNewsContent";
    //校务信息 m2
    public final static String API_XWXXNEWSLIST = "http://hongyan.cqupt.edu.cn/api/xwxxNewsList";
    public final static String API_XWXXNEWSCONTENT = "http://hongyan.cqupt.edu.cn/api/xwxxNewsContent";

    //天气
    public final static String API_WEATHER = "http://www.weather.com.cn/data/cityinfo/101040100.html?appid=213821f9a813579d&private_key=00632f_SmartWeatherAPI_c31aa2d";

    public final static String API_RESTAURANT_LIST = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/shoplist";
    public final static String API_RESTAURANT_DETAIL = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/shopinfo";
    public final static String API_SHOP_COMMENT_LIST = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/comList";
    public final static String API_RESTAURANT_MENUPRAISE = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/praise";
    public final static String API_RESTAURANT_MENULIST = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/menulist";
    public final static String API_SHOP_COMMENT_SEND = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/addCom";
    public final static String API_JOB_LIST = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/jobList";
    public final static String API_JOB_DETAIL = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/jobInfo";
    public final static String API_EATWHAT = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php/admin/shop/shake";

    public final static String API_UPDATA = "http://hongyan.cqupt.edu.cn/app/cyxbsAppUpdate.xml";
    public final static String API_ADVICE = "http://hongyan.cqupt.edu.cn/cyxbs_api_2014/cqupthelp/index.php?s=/admin/shop/registSuggestion";

    /**
     * params
     */
    public static final String PARAM_STU_NUM = "stuNum";
    public static final String PARAM_ID_NUM = "idNum";

}
