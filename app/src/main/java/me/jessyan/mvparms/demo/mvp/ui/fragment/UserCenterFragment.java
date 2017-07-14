package me.jessyan.mvparms.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import me.jessyan.mvparms.demo.di.component.DaggerUserCenterComponent;
import me.jessyan.mvparms.demo.di.module.UserCenterModule;
import me.jessyan.mvparms.demo.mvp.contract.UserCenterContract;
import me.jessyan.mvparms.demo.mvp.presenter.UserCenterPresenter;

import me.jessyan.mvparms.demo.R;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by sfeng on 2017/7/11. <主页 - 个人中心>
 */
public class UserCenterFragment
        extends BaseFragment<UserCenterPresenter>
        implements UserCenterContract.View {

    public static UserCenterFragment newInstance() {
        UserCenterFragment fragment = new UserCenterFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerUserCenterComponent
                .builder()
                .appComponent(appComponent)
                .userCenterModule(new UserCenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_center, container, false);
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