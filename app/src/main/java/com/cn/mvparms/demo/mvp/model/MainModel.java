package com.cn.mvparms.demo.mvp.model;

import android.app.Application;

import com.cn.mvparms.demo.mvp.contract.MainContract;
import com.cn.mvparms.demo.mvp.model.api.service.HomeService;
import com.cn.mvparms.demo.mvp.model.api.service.UserService;
import com.cn.mvparms.demo.mvp.model.entity.BaseRequest;
import com.cn.mvparms.demo.mvp.model.entity.result.AccessResult;
import com.cn.mvparms.demo.mvp.model.entity.result.TokenResult;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class MainModel extends BaseModel implements MainContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MainModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<TokenResult> getRequestToken(String appId, long timeNow, String resultType, String resultVers, String resultMd5, String signBase64) {
        Observable<TokenResult> objectObservable = mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .getRequestToken(appId, timeNow, resultType, resultVers, resultMd5, signBase64);
        return objectObservable;
    }

    @Override
    public Observable<AccessResult> getRequestAccess(String appId, long timestamp, String format, String authVers, String accessToken, String signMethod, String sign) {
        Observable<AccessResult> objectObservable = mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .getRequestAccess(appId, timestamp, format, authVers, accessToken, signMethod, sign);
        return objectObservable;
    }

    @Override
    public Observable<Object> postRequestFirstPage(BaseRequest post) {
        Observable<Object> observable = mRepositoryManager
                .obtainRetrofitService(HomeService.class)
                .getRequestFirstPage(
                        post.getMethod(), post.getParaInfo(), post.getFormat(),
                        post.getAccessToken(), post.getAuthVers(), post.getSignMethod(),
                        post.getTimestamp(), post.getToken(), post.getFields(),post.getClientip(),
                        post.getVers(), post.getAppid(), post.getSign());
        return observable;
    }
}