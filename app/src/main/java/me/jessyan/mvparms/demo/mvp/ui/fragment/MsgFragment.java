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

import me.jessyan.mvparms.demo.di.component.DaggerMsgComponent;
import me.jessyan.mvparms.demo.di.module.MsgModule;
import me.jessyan.mvparms.demo.mvp.contract.MsgContract;
import me.jessyan.mvparms.demo.mvp.presenter.MsgPresenter;

import me.jessyan.mvparms.demo.R;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by sfeng on 2017/7/11. <主页 - 消息>
 */
public class MsgFragment
        extends BaseFragment<MsgPresenter>
        implements MsgContract.View {

    public static MsgFragment newInstance() {
        MsgFragment fragment = new MsgFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMsgComponent
                .builder()
                .appComponent(appComponent)
                .msgModule(new MsgModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_msg, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onActivityCreated
     * 还没执行,setData里调用presenter的方法时,是会报空的,因为dagger注入是在onActivityCreated
     * 方法中执行的,如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

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