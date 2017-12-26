package com.cn.mvparms.demo.mvp.model;

import android.app.Application;

import com.cn.mvparms.demo.mvp.contract.UserCenterContract;
import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Inject;


@ActivityScope
public class UserCenterModel extends BaseModel implements UserCenterContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public UserCenterModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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