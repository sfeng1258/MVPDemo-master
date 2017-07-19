package me.jessyan.mvparms.demo.mvp.model.api.service;

import java.util.List;

import io.reactivex.Observable;
import me.jessyan.mvparms.demo.app.constant.ServiceConstants;
import me.jessyan.mvparms.demo.mvp.model.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 存放通用的一些API
 */
public interface CommonService {

    //获取动态首页
    public static final String SELECT_DYNAMIC_FIRST_CONFIG
            = "/api/mobile-category-service/rs/dynamicFirstPage/selectDynamicFirstConfig";


    @GET(ServiceConstants.FIRST_PAGE)
    Observable<List<User>> getFirstPage(@Query("versionNum") String versionNum, @Query("deviceType") int deviceType);
}
