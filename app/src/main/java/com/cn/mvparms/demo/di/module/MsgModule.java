package com.cn.mvparms.demo.di.module;

import com.cn.mvparms.demo.mvp.contract.BuyerContract;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.cn.mvparms.demo.mvp.model.MsgModel;


@Module
public class MsgModule {
    private BuyerContract.View view;

    /**
     * 构建MsgModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MsgModule(BuyerContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    BuyerContract.View provideMsgView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    BuyerContract.Model provideMsgModel(MsgModel model) {
        return model;
    }
}