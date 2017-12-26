package com.cn.mvparms.demo.mvp.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.mvparms.demo.mvp.ui.fragment.HomeFragment;
import com.cn.mvparms.demo.mvp.ui.fragment.BuyerFragment;
import com.cn.mvparms.demo.mvp.ui.fragment.SellerFragment;
import com.cn.mvparms.demo.mvp.ui.fragment.UserCenterFragment;

import java.util.ArrayList;

/**
 * Created by sfeng on 2017/7/11.
 */
public class MainTabFragmentAdapter
        extends FragmentPagerAdapter {

    private static String[] icontxts = {"首页", "我要买", "我要卖", "我的"};
    private final int[] icons = {
            com.cn.mvparms.demo.R.drawable.ic_action_gold,
            com.cn.mvparms.demo.R.drawable.ic_action_gold,
            com.cn.mvparms.demo.R.drawable.ic_action_gold,
            com.cn.mvparms.demo.R.drawable.ic_action_gold
    };

    private int Count = 4;
    private Context mContext;
    private ArrayList<Fragment> fragments;

    public MainTabFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        if (fragments == null) this.fragments = new ArrayList<>();
        fragments.add(new HomeFragment().newInstance());
        fragments.add(new BuyerFragment().newInstance());
        fragments.add(new SellerFragment().newInstance());
        fragments.add(new UserCenterFragment().newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return Count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

    public View getCustomView(int position) {
        View view = LayoutInflater.from(mContext).inflate(com.cn.mvparms.demo.R.layout.item_main_tab_view, null, false);
        TextView tvTxt = (TextView) view.findViewById(com.cn.mvparms.demo.R.id.tv_txt);
        ImageView ivIcon = (ImageView) view.findViewById(com.cn.mvparms.demo.R.id.iv_icon);
        ivIcon.setBackgroundResource(icons[position]);
        tvTxt.setText(icontxts[position]);
        return view;
    }

}
