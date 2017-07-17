package me.jessyan.mvparms.demo.mvp.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.ui.fragment.HomeFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.MsgFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.SellerFragment;
import me.jessyan.mvparms.demo.mvp.ui.fragment.UserCenterFragment;

/**
 * Created by sfeng on 2017/7/11.
 */
public class MainTabFragmentAdapter
        extends FragmentPagerAdapter {

    private static String[] icontxts = {"首页", "手游", "发布", "端游", "我的"};
    private final int[] icons = {R.drawable.ic_action_gold,
            R.drawable.ic_action_gold, R.drawable.ic_action_gold,
            R.drawable.ic_action_gold, R.drawable.ic_action_gold};

    private int Count = 5;
    private Context mContext;
    private ArrayList<Fragment> fragments;

    public MainTabFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        if (fragments == null) this.fragments = new ArrayList<>();
        fragments.add(new HomeFragment().newInstance());
        fragments.add(new SellerFragment().newInstance());
        fragments.add(new SellerFragment().newInstance());
        fragments.add(new MsgFragment().newInstance());
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_tab_view, null, false);
        TextView tvTxt = (TextView) view.findViewById(R.id.tv_txt);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        ivIcon.setBackgroundResource(icons[position]);
        tvTxt.setText(icontxts[position]);
        return view;
    }

}
