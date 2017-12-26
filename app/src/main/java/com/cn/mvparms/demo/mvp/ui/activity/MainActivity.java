package com.cn.mvparms.demo.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;

import com.cn.mvparms.demo.R;
import com.cn.mvparms.demo.app.constant.DefaultConstant;
import com.cn.mvparms.demo.app.utils.StatusBarUtils;
import com.cn.mvparms.demo.di.component.DaggerMainComponent;
import com.cn.mvparms.demo.di.module.MainModule;
import com.cn.mvparms.demo.mvp.contract.MainContract;
import com.cn.mvparms.demo.mvp.presenter.MainPresenter;
import com.cn.mvparms.demo.mvp.ui.adapter.MainTabFragmentAdapter;
import com.cn.mvparms.demo.mvp.ui.widget.NoScrollViewPager;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MainActivity
        extends BaseActivity<MainPresenter>
        implements MainContract.View {

    @BindView(R.id.ViewPager_banner)
    NoScrollViewPager mViewPager;
    @BindView(R.id.TabLayout)
    TabLayout mTabLayout;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtils.enableTranslucentStatusbar(this);  //设置状态栏为透明色
        MainTabFragmentAdapter mainTabFragmentAdapter
                = new MainTabFragmentAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mainTabFragmentAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mainTabFragmentAdapter.getCustomView(i));
        }

        //设置当前显示的Fragment
        int currentPage = getIntent().getIntExtra(DefaultConstant.CURRENT_PAGE, 0);
        if (currentPage == -1) {
            mTabLayout.getTabAt(0);
        } else {
            mTabLayout.getTabAt(currentPage);
        }

        RequestList();
    }

    private void RequestList() {
        mPresenter.getRequestToken();
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
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}