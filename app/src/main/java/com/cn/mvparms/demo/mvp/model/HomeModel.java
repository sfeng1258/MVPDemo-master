package com.cn.mvparms.demo.mvp.model;

import android.app.Application;

import com.cn.mvparms.demo.mvp.contract.HomeContract;
import com.cn.mvparms.demo.mvp.model.api.service.HomeService;
import com.cn.mvparms.demo.mvp.model.entity.BaseRequest;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class HomeModel extends BaseModel implements HomeContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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