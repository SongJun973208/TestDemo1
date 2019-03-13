package org.sj.testdemo.zxing;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aries.ui.view.title.TitleBarView;
import com.flyco.tablayout.SlidingTabLayout;

import org.sj.testdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextRefresh extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_refresh);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Toast.makeText(this, "chushihua", Toast.LENGTH_SHORT).show();
        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mFragments.add(TestFragment.getInstance(i));
        }
        mVpTypeViewPager.setOffscreenPageLimit(mTitles.length);
        mVpTypeViewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        mStlTypeTable.setViewPager(mVpTypeViewPager);
    }
}
