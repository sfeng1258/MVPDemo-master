package com.cn.mvparms.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.mvparms.demo.di.module.SellerModule;
import com.cn.mvparms.demo.mvp.contract.SellerContract;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import com.cn.mvparms.demo.di.component.DaggerSellerComponent;
import com.cn.mvparms.demo.mvp.presenter.SellerPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by sfeng on 2017/7/11. <主页 - 我要卖>
 */
public class SellerFragment
        extends BaseFragment<SellerPresenter>
        implements SellerContract.View {

    public static SellerFragment newInstance() {
        SellerFragment fragment = new SellerFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerSellerComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .sellerModule(new SellerModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.cn.mvparms.demo.R.layout.fragment_seller, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

}