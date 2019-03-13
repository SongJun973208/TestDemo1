package org.sj.testdemo.zxing;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 *
 * Created by 宋俊 on 2017/5/5.
 */

public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private String[] mTitles;
    public FragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }
    public FragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] mTitles) {
        super(fm);
        this.fragmentList=fragmentList;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if(mTitles != null && mTitles.length > position){
            return mTitles[position];
        }else{
            return "";
        }
    }
}
