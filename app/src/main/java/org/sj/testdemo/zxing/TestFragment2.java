package org.sj.testdemo.zxing;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aries.ui.view.title.TitleBarView;
import com.flyco.tablayout.SlidingTabLayout;

import org.sj.testdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestFragment2 extends Fragment {

    @BindView(R.id.titleBar)
    TitleBarView mTitleBar;
    //    @BindView(R.id.bga_tj_banner)
//    BGABanner mBgaTjBanner;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.stl_type_table)
    SlidingTabLayout mStlTypeTable;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.vp_type_view_pager)
    ViewPager mVpTypeViewPager;
    private ArrayList<Fragment> mFragments;
    private String[] mTitles = {"北京","北京","北京","北京","北京","北京"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_test2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        Toast.makeText(getActivity(), "chushihua", Toast.LENGTH_SHORT).show();
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(TestFragment.getInstance(i));
        }
        mVpTypeViewPager.setOffscreenPageLimit(mTitles.length);
        mVpTypeViewPager.setAdapter(new FragmentViewPagerAdapter(getChildFragmentManager(), mFragments, mTitles));
        mStlTypeTable.setViewPager(mVpTypeViewPager);
    }

}
