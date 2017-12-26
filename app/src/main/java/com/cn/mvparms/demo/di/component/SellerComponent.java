package com.cn.mvparms.demo.di.component;

import com.cn.mvparms.demo.di.module.SellerModule;
import com.cn.mvparms.demo.mvp.ui.fragment.SellerFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = SellerModule.class, dependencies = AppComponent.class)
public interface SellerComponent {
    void inject(SellerFragment fragment);
}