package com.cn.mvparms.demo.mvp.model.entity;

import android.content.Context;

import com.cn.mvparms.demo.app.constant.DataConstant;
import com.cn.mvparms.demo.app.utils.Base64Utils;
import com.cn.mvparms.demo.app.utils.NetUtils;
import com.cn.mvparms.demo.app.utils.VersionUtil;
import com.cn.mvparms.demo.mvp.model.api.Api;
import com.jess.arms.utils.DataHelper;

/**
 * Created by sfeng on 2017/12/13. <请求模板>
 */

public class BaseRequest {

    private String method;
    private String paraInfo;
    private String format;
    private String accessToken;
    private String authVers;
    private String signMethod;
    private long timestamp;
    private String token;
    private String fields;
    private String clientip;
    private String vers;
    private String appid;
    private String sign;

    @Override
    public String toString() {
        return "BaseRequest{" +
                "method='" + method + '\'' +
                ", paraInfo='" + paraInfo + '\'' +
                ", format='" + format + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", authVers='" + authVers + '\'' +
                ", signMethod='" + signMethod + '\'' +
                ", timestamp=" + timestamp +
                ", token='" + token + '\'' +
                ", fields='" + fields + '\'' +
                ", clientip='" + clientip + '\'' +
                ", vers='" + vers + '\'' +
                ", appid='" + appid + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }

    public BaseRequest(Context context, String method, String paraInfo) {
        this.vers = Api.VERS;
        setData(context, method, paraInfo);
    }

    public BaseRequest(Context context, String method, String paraInfo, String vers) {
        this.vers = vers;
        setData(context, method, paraInfo);
    }

    private void setData(Context context, String method, String paraInfo) {
        this.method = method;
        this.paraInfo = paraInfo;
        this.format = Api.RESULT_TYPE;
        this.accessToken = DataHelper.getStringSF(context, DataConstant.AccessToken);
        this.authVers = Api.RESULT_VERSION;
        this.signMethod = Api.RESULT_MD5;
        this.timestamp = System.currentTimeMillis() / 1000;
        this.token = "";
        this.fields = "";
        this.clientip = VersionUtil.getLocalIpAddress(context);
        this.appid = Api.APP_ID;

        String newString = accessToken + "&" + appid + "&" + authVers + "&" + clientip + "&" +
                "" + "&" + format + "&" + method + "&" + paraInfo + "&" + signMethod + "&" +
                String.valueOf(timestamp) + "&" + token + "&" + "POST:" + Api.URL_REST + "&" + "";
        String sign = Api.KAYVALUE + "&" + NetUtils.SetMD5(newString) + "&" + DataHelper.getStringSF(context, DataConstant.AccessSecret);
        this.sign = Base64Utils.encodeBytes(sign.getBytes());
    }

    public String getMethod() {
        return method;
    }

    public String getParaInfo() {
        return paraInfo;
    }

    public String getFormat() {
        return format;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAuthVers() {
        return authVers;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getToken() {
        return token;
    }

    public String getFields() {
        return fields;
    }

    public String getClientip() {
        return clientip;
    }

    public String getVers() {
        return vers;
    }

    public String getAppid() {
        return appid;
    }

    public String getSign() {
        return sign;
    }
}
