package me.jessyan.mvparms.demo.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import me.jessyan.mvparms.demo.di.component.DaggerHomePageComponent;
import me.jessyan.mvparms.demo.di.module.HomePageModule;
import me.jessyan.mvparms.demo.mvp.contract.HomePageContract;
import me.jessyan.mvparms.demo.mvp.presenter.HomePagePresenter;

import me.jessyan.mvparms.demo.R;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by sfeng on 2017/7/11. <首页 - 端游/手游>
 */
public class HomePageFragment
        extends BaseFragment<HomePagePresenter>
        implements HomePageContract.View {

    private final static String TYPE_TAG = "TYPE_TAG";

    public static HomePageFragment newInstance(int page) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TYPE_TAG, page);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerHomePageComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homePageModule(new HomePageModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page, container, false);
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