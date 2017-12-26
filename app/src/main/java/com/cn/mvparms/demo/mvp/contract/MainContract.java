package com.cn.mvparms.demo.mvp.contract;

import com.cn.mvparms.demo.mvp.model.entity.BaseRequest;
import com.cn.mvparms.demo.mvp.model.entity.result.AccessResult;
import com.cn.mvparms.demo.mvp.model.entity.result.TokenResult;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;


public interface MainContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<TokenResult> getRequestToken(
                String appId, long timeNow, String resultType, String resultVers, String resultMd5, String signBase64);

        Observable<AccessResult> getRequestAccess(
                String appId, long timestamp, String format, String authVers, String accessToken, String signMethod, String sign);

        Observable<Object> postRequestFirstPage(BaseRequest post);
    }
}