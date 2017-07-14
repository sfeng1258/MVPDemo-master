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

    private int Count = 5;
    private static String[] icontxts = {"首页", "手游", "发布", "端游", "我的"};
    private final int[] icons = {R.drawable.selector_home_index, R.drawable.selector_seller_index,
            R.drawable.selector_msg_index, R.drawable.selector_my_index};
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
        return icontxts[position];
    }

//    public View getCustomView(int position) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_tab_view, null, false);
//        TextView icontxt = (TextView) view.findViewById(R.id.icontext);
//        ImageView icon = (ImageView) view.findViewById(R.id.icon);
//        icon.setBackgroundResource(icons[position]);
//        icontxt.setText(icontxts[position]);
//        return view;
//    }

}
