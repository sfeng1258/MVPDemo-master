package com.cn.mvparms.demo.di.component;

import com.cn.mvparms.demo.mvp.ui.fragment.BuyerFragment;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.cn.mvparms.demo.di.module.MsgModule;

@ActivityScope
@Component(modules = MsgModule.class, dependencies = AppComponent.class)
public interface MsgComponent {
    void inject(BuyerFragment fragment);
}