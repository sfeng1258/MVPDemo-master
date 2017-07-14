package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.SearchModule;

import me.jessyan.mvparms.demo.mvp.ui.activity.home.SearchActivity;

@ActivityScope
@Component(modules = SearchModule.class, dependencies = AppComponent.class)
public interface SearchComponent {
    void inject(SearchActivity activity);
}