package me.jessyan.mvparms.demo.mvp.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.ui.fragment.HomePageFragment;

/**
 * Created by sfeng on 2017/7/11.
 */
public class HomeTabFragmentAdapter extends FragmentPagerAdapter {
    private int Count = 2;
    private static String[] icontxts = {"端游", "手游"};
    private final int[] icons = {R.drawable.selector_home_index, R.drawable.selector_seller_index,
            R.drawable.selector_msg_index, R.drawable.selector_my_index};
    private ArrayList<Fragment> fragments;

    public HomeTabFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        if (fragments == null) this.fragments = new ArrayList<>();
        fragments.add(new HomePageFragment().newInstance(0));
        fragments.add(new HomePageFragment().newInstance(1));
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
        return icontxts[position];
    }
}
