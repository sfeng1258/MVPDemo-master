package com.cn.mvparms.demo.mvp.presenter;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.cn.mvparms.demo.app.constant.DataConstant;
import com.cn.mvparms.demo.app.utils.NetUtils;
import com.cn.mvparms.demo.app.utils.RequestUtils;
import com.cn.mvparms.demo.app.utils.RxUtils;
import com.cn.mvparms.demo.mvp.contract.MainContract;
import com.cn.mvparms.demo.mvp.model.api.Api;
import com.cn.mvparms.demo.mvp.model.entity.BaseRequest;
import com.cn.mvparms.demo.mvp.model.entity.result.AccessResult;
import com.cn.mvparms.demo.mvp.model.entity.result.TokenResult;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.widget.imageloader.ImageLoader;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

import static com.cn.mvparms.demo.mvp.ui.fragment.HomeFragment.CURRENT_CONFIG_VERSION;

@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public MainPresenter(MainContract.Model model, MainContract.View rootView
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

    public void getRequestToken() {
        String appid = Api.APP_ID;
        long timestamp = System.currentTimeMillis() / 1000;
        String format = Api.RESULT_TYPE;
        String authVers = "1.0";
        String signMethod = Api.RESULT_MD5;
        String sign = NetUtils.SignBase64(timestamp, "", "", false);

        mModel.getRequestToken(appid, timestamp, format, authVers, signMethod, sign)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<TokenResult>(mErrorHandler) {
                    @Override
                    public void onNext(TokenResult result) {
                        Log.e("~~~ Token成功", result.toString());
                        getRequestAccess(result);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("~~~ Token失败", e.toString());
                        Toast.makeText(mApplication, "~~~ Token失败" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getRequestAccess(TokenResult result) {
        String appid = Api.APP_ID;
        long timestamp = System.currentTimeMillis() / 1000;
        String format = Api.RESULT_TYPE;
        String authVers = "1.0";
        String signMethod = Api.RESULT_MD5;
        String sign = NetUtils.SignBase64(
                timestamp, result.getBizResult().getRequestSecret(), result.getBizResult().getRequestToken(), true);

        mModel.getRequestAccess(appid, timestamp, format, authVers, result.getBizResult().getRequestToken(), signMethod, sign)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.bindToLifecycle(mRootView))
                .subscribe(new ErrorHandleSubscriber<AccessResult>(mErrorHandler) {
                    @Override
                    public void onNext(AccessResult result) {
                        if (null != result && result.getStatus() == 0) {
                            DataHelper.setStringSF(mApplication, DataConstant.AccessSecret,result.getBizResult().getAccessSecret());
                            DataHelper.setStringSF(mApplication, DataConstant.AccessToken,result.getBizResult().getAccessToken());
                        }

                        Log.e("~~~ Access成功", result.toString());
//                        Toast.makeText(mApplication, "~~~ Access成功" + result.toString(), Toast.LENGTH_SHORT).show();

                        BaseRequest baseRequest = RequestUtils
                                .getFirstPageRequest(mApplication, CURRENT_CONFIG_VERSION, Api.VALUE_PLATFORM_ANDROID, String.valueOf(System.currentTimeMillis()));
                        postRequestFirstPage(baseRequest);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("~~~ Access失败", e.toString());
                        Toast.makeText(mApplication, "~~~ Access失败" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
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