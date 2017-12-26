package com.cn.mvparms.demo.di.component;

import com.cn.mvparms.demo.di.module.HomePageModule;
import com.cn.mvparms.demo.mvp.ui.fragment.HomePageFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = HomePageModule.class, dependencies = AppComponent.class)
public interface HomePageComponent {
    void inject(HomePageFragment fragment);
}