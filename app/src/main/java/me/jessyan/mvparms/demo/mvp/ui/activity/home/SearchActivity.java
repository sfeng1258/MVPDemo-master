package me.jessyan.mvparms.demo.mvp.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.di.component.DaggerSearchComponent;
import me.jessyan.mvparms.demo.di.module.SearchModule;
import me.jessyan.mvparms.demo.mvp.contract.SearchContract;
import me.jessyan.mvparms.demo.mvp.presenter.SearchPresenter;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class SearchActivity
        extends BaseActivity<SearchPresenter>
        implements SearchContract.View {

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
        return R.layout.activity_search;
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


}