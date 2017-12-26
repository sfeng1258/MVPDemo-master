package com.cn.mvparms.demo.di.module;

import com.cn.mvparms.demo.mvp.contract.MainContract;
import com.cn.mvparms.demo.mvp.model.MainModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class MainModule {
    private MainContract.View view;

    /**
     * 构建HomeModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View provideHomeView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MainContract.Model provideHomeModel(MainModel model) {
        return model;
    }
}