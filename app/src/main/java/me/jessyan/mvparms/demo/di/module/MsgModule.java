package me.jessyan.mvparms.demo.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import me.jessyan.mvparms.demo.mvp.contract.MsgContract;
import me.jessyan.mvparms.demo.mvp.model.MsgModel;


@Module
public class MsgModule {
    private MsgContract.View view;

    /**
     * 构建MsgModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public MsgModule(MsgContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MsgContract.View provideMsgView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    MsgContract.Model provideMsgModel(MsgModel model) {
        return model;
    }
}