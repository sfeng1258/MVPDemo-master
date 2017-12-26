package com.cn.mvparms.demo.mvp.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.cn.mvparms.demo.app.constant.DefaultConstant;
import com.cn.mvparms.demo.di.component.DaggerSearchComponent;
import com.cn.mvparms.demo.di.module.SearchModule;
import com.cn.mvparms.demo.mvp.contract.SearchContract;
import com.cn.mvparms.demo.mvp.presenter.SearchPresenter;
import com.cn.mvparms.demo.mvp.ui.activity.MainActivity;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class SearchActivity
        extends BaseActivity<SearchPresenter>
        implements SearchContract.View {

    @BindView(com.cn.mvparms.demo.R.id.ll_header_search)
    LinearLayout llHeaderSearch;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerSearchComponent
                .builder()
                .appComponent(appComponent)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return com.cn.mvparms.demo.R.layout.activity_search;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(com.cn.mvparms.demo.R.id.ll_header_search)
    public void onViewClicked() {
        launchActivity(new Intent(SearchActivity.this, MainActivity.class)
                .putExtra(DefaultConstant.CURRENT_PAGE, 2));
    }
}