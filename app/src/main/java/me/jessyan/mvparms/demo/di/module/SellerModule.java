package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.SellerContract;
import me.jessyan.mvparms.demo.mvp.model.SellerModel;


@Module
public class SellerModule {
    private SellerContract.View view;

    /**
     * 构建SellerModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SellerModule(SellerContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    SellerContract.View provideSellerView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    SellerContract.Model provideSellerModel(SellerModel model) {
        return model;
    }
}