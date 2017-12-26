package com.cn.mvparms.demo.di.component;

import com.cn.mvparms.demo.di.module.HomeModule;
import com.cn.mvparms.demo.mvp.ui.fragment.HomeFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(HomeFragment fragment);
}