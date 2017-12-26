package com.cn.mvparms.demo.mvp.model.api.service;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 存放通用的一些API
 */
public interface HomeService {

    /**
     * 获取 动态首页
     */
    @POST("/rest.do")
    Observable<Object> getRequestFirstPage(
            @Query("method") String method,
            @Query("paraInfo") String paraInfo,
            @Query("format") String format,
            @Query("accessToken") String accessToken,
            @Query("authVers") String authVers,
            @Query("signMethod") String signMethod,
            @Query("timestamp") long timestamp,
            @Query("token") String token,
            @Query("fields") String fields,
            @Query("clientip") String clientip,
            @Query("vers") String vers,
            @Query("appid") String appid,
            @Query("sign") String sign);
}
