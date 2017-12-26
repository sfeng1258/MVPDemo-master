package com.cn.mvparms.demo.mvp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cn.mvparms.demo.R;
import com.cn.mvparms.demo.app.utils.StatusBarUtils;
import com.cn.mvparms.demo.di.component.DaggerHomeComponent;
import com.cn.mvparms.demo.di.module.HomeModule;
import com.cn.mvparms.demo.mvp.contract.HomeContract;
import com.cn.mvparms.demo.mvp.presenter.HomePresenter;
import com.cn.mvparms.demo.mvp.ui.activity.home.SearchActivity;
import com.cn.mvparms.demo.mvp.ui.adapter.HomeTabFragmentAdapter;
import com.cn.mvparms.demo.mvp.ui.widget.ObservableScrollView;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by sfeng on 2017/7/11. <主页 - 首页>
 */
public class HomeFragment
        extends BaseFragment<HomePresenter>
        implements HomeContract.View, ObservableScrollView.OnObservableScrollViewListener {

    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.ll_header_search)
    LinearLayout llHeaderSearch;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.ll_scan)
    LinearLayout llScan;
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.ViewPager)
    ViewPager mViewPager;
    @BindView(R.id.ViewPager_banner)
    Banner mViewPagerBanner;
    @BindView(R.id.mObservableScrollView)
    ObservableScrollView mObservableScrollView;

    private int mHeight;

    //后台配置的首页版本号，不一定和app的版本号相同
    public static final String CURRENT_CONFIG_VERSION = "2.7.0";

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.cn.mvparms.demo.R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initPost();         //请求
        initTitle();        //渐变的标题栏
        initViewPager();    //关联ViewPager
        initBanner();       //顶部广告轮播图
    }

    private void initPost() {
//        BaseRequest baseRequest = RequestUtils
//                .getFirstPageRequest(getContext(), CURRENT_CONFIG_VERSION, Api.VALUE_PLATFORM_ANDROID, "");
//        mPresenter.postRequestFirstPage(baseRequest);
    }

    private void initTitle() {
        StatusBarUtils.enableTranslucentStatusbar(mActivity);  //设置状态栏为透明色
        ViewTreeObserver viewTreeObserver = mViewPagerBanner.getViewTreeObserver();
        viewTreeObserver.addOnScrollChangedListener(() -> {
            mViewPagerBanner.getViewTreeObserver()
                    .removeGlobalOnLayoutListener(() -> {
                    });
            mHeight = mViewPagerBanner.getHeight() - llHeaderSearch.getHeight();
            mObservableScrollView.setOnObservableScrollViewListener(HomeFragment.this);
        });
    }

    private void initViewPager() {
        HomeTabFragmentAdapter mainTabFragmentAdapter =
                new HomeTabFragmentAdapter(getChildFragmentManager(), mContext);
        mViewPager.setAdapter(mainTabFragmentAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mainTabFragmentAdapter.getCustomView(i));
        }
        //设置默认选择位置为第一个
        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    //轮播
    private void initBanner() {
        //圆形指示器
        mViewPagerBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //指示器居中
        mViewPagerBanner.setIndicatorGravity(BannerConfig.CENTER);
        ArrayList<String> images = new ArrayList<>();
        images.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        images.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
        images.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");

        mViewPagerBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object obj, ImageView imageView) {
                Glide.with(context).load(obj).into(imageView);
                Uri uri = Uri.parse((String) obj);
                imageView.setImageURI(uri);
            }
        });

        mViewPagerBanner.setImages(images);
        mViewPagerBanner.setBannerAnimation(Transformer.Default);
//        mViewPagerBanner.setBannerTitles(titles);
        mViewPagerBanner.isAutoPlay(true);
        mViewPagerBanner.setDelayTime(3000);
        mViewPagerBanner.setIndicatorGravity(BannerConfig.CENTER);
        mViewPagerBanner.start();
    }

    @Override
    public void setData(Object data) {

    }

    @OnClick({R.id.ll_search, R.id.iv_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_search:
                launchActivity(new Intent(mContext, SearchActivity.class));
                break;
            case R.id.iv_scan:

                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            llHeaderSearch.setBackgroundColor(Color.argb(0, 63, 168, 98));      //顶部图处于最顶部，标题栏透明
        } else if (t > 0 && t < mHeight) {                                      //滑动过程中，渐变
            float scale = (float) t / mHeight;                                  //算出滑动距离比例
            float alpha = (255 * scale);                                        //得到透明度
            llHeaderSearch.setBackgroundColor(Color.argb((int) alpha, 63, 168, 98));
        } else {
            llHeaderSearch.setBackgroundColor(Color.argb(255, 63, 168, 98));    //过顶部图区域，标题栏定色
        }
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
        mActivity.finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}