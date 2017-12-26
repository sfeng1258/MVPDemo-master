package com.cn.mvparms.demo.app.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cn.mvparms.demo.app.constant.ServiceConstants;
import com.cn.mvparms.demo.mvp.model.entity.BaseRequest;
import com.google.gson.JsonObject;
import com.jess.arms.utils.LogUtils;

/**
 * Created by sfeng on 2017/12/13. <请求工具类>
 */
public class RequestUtils {

    @NonNull
    private static BaseRequest getBaseRequest(Context context, String method, String paraInfo) {
        BaseRequest baseRequest = new BaseRequest(context, method, paraInfo);
        LogUtils.debugInfo(" ~~~ baseRequest ~~~ " + baseRequest.toString());
        return baseRequest;
    }

    /**
     * 获取动态首页
     */
    public static BaseRequest getFirstPageRequest(Context context, String FuncVersion, String DeviceType, String ModifiedTime) {
        JsonObject object = new JsonObject();
        object.addProperty("FuncVersion", FuncVersion);
        object.addProperty("DeviceType", DeviceType);
        object.addProperty("ModifiedTime", ModifiedTime);
        return getBaseRequest(context, ServiceConstants.FIRST_PAGE, object.toString());
    }

}
