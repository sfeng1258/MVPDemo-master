package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.UserCenterModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.UserCenterFragment;

@ActivityScope
@Component(modules = UserCenterModule.class, dependencies = AppComponent.class)
public interface UserCenterComponent {
    void inject(UserCenterFragment fragment);
}