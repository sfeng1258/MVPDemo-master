package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.MsgModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.MsgFragment;

@ActivityScope
@Component(modules = MsgModule.class, dependencies = AppComponent.class)
public interface MsgComponent {
    void inject(MsgFragment fragment);
}