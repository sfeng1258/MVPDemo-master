package com.cn.mvparms.demo.di.component;

import com.cn.mvparms.demo.di.module.SearchModule;
import com.cn.mvparms.demo.mvp.ui.activity.home.SearchActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

@ActivityScope
@Component(modules = SearchModule.class, dependencies = AppComponent.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}