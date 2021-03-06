package com.cn.mvparms.demo.mvp.model;

import android.app.Application;

import com.cn.mvparms.demo.mvp.contract.SellerContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;


@ActivityScope
public class SellerModel extends BaseModel implements SellerContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public SellerModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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

}