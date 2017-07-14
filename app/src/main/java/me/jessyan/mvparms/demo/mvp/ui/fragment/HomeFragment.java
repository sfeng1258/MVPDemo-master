package me.jessyan.mvparms.demo.mvp.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
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

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.app.utils.StatusBarUtils;
import me.jessyan.mvparms.demo.di.component.DaggerHomeComponent;
import me.jessyan.mvparms.demo.di.module.HomeModule;
import me.jessyan.mvparms.demo.mvp.contract.HomeContract;
import me.jessyan.mvparms.demo.mvp.presenter.HomePresenter;
import me.jessyan.mvparms.demo.mvp.ui.activity.home.SearchActivity;
import me.jessyan.mvparms.demo.mvp.ui.adapter.HomeTabFragmentAdapter;
import me.jessyan.mvparms.demo.mvp.ui.widget.ObservableScrollView;

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
    ViewPager mViewPagerBanner;
    @BindView(R.id.mObservableScrollView)
    ObservableScrollView mObservableScrollView;

    Unbinder unbinder;
    private int mHeight;

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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        StatusBarUtils.enableTranslucentStatusbar(mActivity);  //设置状态栏为透明色

        HomeTabFragmentAdapter mainTabFragmentAdapter =
                new HomeTabFragmentAdapter(getChildFragmentManager(), mContext);
        mViewPager.setAdapter(mainTabFragmentAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.getTabAt(0);

        ViewTreeObserver viewTreeObserver = mViewPagerBanner.getViewTreeObserver();
        viewTreeObserver.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                mViewPagerBanner.getViewTreeObserver().removeOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                    }
                });
                mHeight = mViewPagerBanner.getHeight() - llHeaderSearch.getHeight();
                mObservableScrollView.setOnObservableScrollViewListener(HomeFragment.this);
            }
        });

    }


//    /*轮播*/
//    private void initBanner() {
//        //圆形指示器
//        header.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        //指示器居中
//        header.setIndicatorGravity(BannerConfig.CENTER);
//        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
//        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
//        img.add("http://m.beequick.cn/static/bee/img/m/boot_logo-275a61e3.png");
//        header.setImageLoader(new ImageLoader() {
//            @Override
//            public void displayImage(Context context, Object o, ImageView imageView) {
//                Picasso.with(context)
//                        .load(url)
//                        .into(imageView);
//            }
//        });
//        header.setImages(img);
//        header.start();
//    }

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
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {
        if (t <= 0) {
            //顶部图处于最顶部，标题栏透明
            llHeaderSearch.setBackgroundColor(Color.argb(0, 63, 168, 98));
        } else if (t > 0 && t < mHeight) {
            //滑动过程中，渐变
            float scale = (float) t / mHeight;//算出滑动距离比例
            float alpha = (255 * scale);//得到透明度
            llHeaderSearch.setBackgroundColor(Color.argb((int) alpha, 63, 168, 98));
        } else {
            //过顶部图区域，标题栏定色
            llHeaderSearch.setBackgroundColor(Color.argb(255, 63, 168, 98));
        }
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}