package com.cn.mvparms.demo.di.component;

import com.cn.mvparms.demo.di.module.MainModule;
import com.cn.mvparms.demo.mvp.ui.activity.MainActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}