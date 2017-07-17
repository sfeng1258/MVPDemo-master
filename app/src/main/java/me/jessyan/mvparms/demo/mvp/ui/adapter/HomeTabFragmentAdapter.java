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

import org.w3c.dom.Text;

import java.util.ArrayList;

import me.jessyan.mvparms.demo.R;
import me.jessyan.mvparms.demo.mvp.ui.fragment.HomePageFragment;

/**
 * Created by sfeng on 2017/7/11.
 */
public class HomeTabFragmentAdapter extends FragmentPagerAdapter {
    private int Count = 2;
    private static String[] icontxts = {"端游", "手游"};
    private final int[] icons = {R.drawable.ic_action_gold, R.drawable.ic_action_gold};
    private ArrayList<Fragment> fragments;
    private Context mContext;

    public HomeTabFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        if (fragments == null) this.fragments = new ArrayList<>();
        fragments.add(new HomePageFragment().newInstance(0));
        fragments.add(new HomePageFragment().newInstance(1));
        this.mContext = context;
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_page_tab_view, null, false);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tvTxt = (TextView) view.findViewById(R.id.tv_txt);
        ivIcon.setBackgroundResource(icons[position]);
        tvTxt.setText(icontxts[position]);
        return view;
    }
}
