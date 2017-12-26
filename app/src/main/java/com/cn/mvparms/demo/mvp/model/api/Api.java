package com.cn.mvparms.demo.mvp.model.api;

/**
 * Created by jess on 8/5/16 11:25
 * contact with jess.yan.effort@gmail.com
 */
public interface Api {

    //测试服务器
//	  public static String URL = "http://192.168.130.54";

    //正式服务器
    public static String URL = "http://routeapi.5173.com";
    public static String URLTWO = "http://passport.5173.com";

    // 线下 e5296215c29c4c2e97079b4d33357f1d  线上 a6c781e34a004a1fb6a81602c102c05e
    // 需要线上环境时，全部切换为线上，线下同理
    public static final String CATE_ID_MOBILE_OFFLINE_GAME = "a6c781e34a004a1fb6a81602c102c05e";//手游类目id-帐号-线下
    public static final String CATE_ID_MOBILE_ONLINE_GAME = "a6c781e34a004a1fb6a81602c102c05e";//手游类目id-帐号-线上

    // 线下 http://192.168.130.143:80/BizOfferV2/UploadFile  线上 http://sy.5173.com/BizOfferV2/UploadFile
    // 需要线上环境时，全部切换为线上，线下同理
    public static final String IMAGE_UPLOAD_OFFLINE_URL = "http://sy.5173.com/BizOfferV2/UploadFile"; //手游图片发布上传地址
    public static final String IMAGE_UPLOAD_ONLINE_URL = "http://sy.5173.com/BizOfferV2/UploadFile"; //手游图片发布上传地址

    /**
     * 服务器ip
     */
    public static final String SERVICEIP = "POST:" + URL + "/rest.do";// 步骤一线上POST:http://routeapi.5173.com/rest.do
    public static final String REQUESTCOMMONSTRING = URL + "/rest.do?";
    public static final String REQUESTSTRING = URL + "/request.do?";
    public static final String ACCESSSTRING = URL + "/access.do?";
    public static final String NEWREQUESTCOMMONSTRING = URLTWO + "/passport/";

    /**
     * 请求成功 返回状态
     */
    public final String RequestSuccess = "0";

    /**
     * 设置服务器返回数据类型
     */
    public static final String RESULT_TYPE = "json";

    /**
     * 设置服务器返回加密类型
     */
    public static final String RESULT_MD5 = "md5";

    /**
     * 设置用户版本
     */
    public static final String RESULT_VERSION = "1.0";

    /**
     * 版本
     */
    public static final String VERS = "1.0";

    /**
     * AppId 线上
     */
    public static final String APP_ID = "and5173";

    /**
     * 签名用的字段
     */
    public static final String KAYVALUE = "and5173KUBAO";// 修改线下

    /**
     * 一般请求接口
     */
    public static String URL_REST = URL + "/rest.do";

    public static final String VALUE_PLATFORM_ANDROID = "Android";
}
