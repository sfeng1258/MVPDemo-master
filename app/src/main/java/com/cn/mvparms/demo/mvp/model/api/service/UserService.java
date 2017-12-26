package com.cn.mvparms.demo.mvp.model.api.service;

import com.cn.mvparms.demo.mvp.model.entity.result.AccessResult;
import com.cn.mvparms.demo.mvp.model.entity.result.TokenResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 存放关于用户的一些api
 */
public interface UserService {

    /**
     * 获取Token
     */
    @GET("/request.do?")
    Observable<TokenResult> getRequestToken(
            @Query("appid") String appid,
            @Query("timestamp") long timestamp,
            @Query("format") String format,
            @Query("authVers") String authVers,
            @Query("signMethod") String signMethod,
            @Query("sign") String sign
    );

    /**
     * 获取 Access
     */
    @GET("/access.do?")
    Observable<AccessResult> getRequestAccess(
            @Query("appid") String appid,
            @Query("timestamp") long timestamp,
            @Query("format") String format,
            @Query("authVers") String authVers,
            @Query("requesttoken") String requesttoken,
            @Query("signMethod") String signMethod,
            @Query("sign") String sign
    );

}
