package com.cn.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.cn.mvparms.demo.mvp.contract.HomePageContract;
import com.cn.mvparms.demo.mvp.model.HomePageModel;


@Module
public class HomePageModule {
    private HomePageContract.View view;

    /**
     * 构建HomePageModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public HomePageModule(HomePageContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HomePageContract.View provideHomePageView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    HomePageContract.Model provideHomePageModel(HomePageModel model) {
        return model;
    }
}