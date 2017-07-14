package me.jessyan.mvparms.demo.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import me.jessyan.mvparms.demo.di.module.SellerModule;

import me.jessyan.mvparms.demo.mvp.ui.fragment.SellerFragment;

@ActivityScope
@Component(modules = SellerModule.class, dependencies = AppComponent.class)
public interface SellerComponent {
    void inject(SellerFragment fragment);
}