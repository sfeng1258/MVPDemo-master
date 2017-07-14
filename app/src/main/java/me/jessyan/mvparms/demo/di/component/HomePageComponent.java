package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.HomePageModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.HomePageFragment;

@ActivityScope
@Component(modules = HomePageModule.class, dependencies = AppComponent.class)
public interface HomePageComponent {
    void inject(HomePageFragment fragment);
}