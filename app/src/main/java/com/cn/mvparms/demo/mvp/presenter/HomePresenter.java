package com.cn.mvparms.demo.mvp.presenter;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.cn.mvparms.demo.app.utils.RxUtils;
import com.cn.mvparms.demo.mvp.contract.HomeContract;
import com.cn.mvparms.demo.mvp.model.entity.BaseRequest;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void postRequestFirstPage(BaseRequest post) {
        mModel.postRequestFirstPage(post)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<Object>(mErrorHandler) {
                    @Override
                    public void onNext(Object result) {
                        Log.e("~~~ first ~~~ 成功", result.toString());
                        Toast.makeText(mApplication, "~~~ first ~~~ 成功" + result.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("~~~ first ~~~ 失败", e.toString());
                        Toast.makeText(mApplication, "~~~ first ~~~ 失败" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}