package org.sj.testdemo.zxing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.sj.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class TestActivity2 extends BaseActivity {

    @BindView(R.id.main_NoTouchViewPager)
    ViewPager mainNoTouchViewPager;
    @BindView(R.id.main_PageBottomTabLayout)
    PageNavigationView mainPageBottomTabLayout;

    NavigationController navigationController;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void setTitleBar() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_test2;
    }

    @Override
    protected void initView(Bundle var1) {
        fragmentList.add(new TestFragment2());
        fragmentList.add(TestFragment.getInstance(11));
        fragmentList.add(TestFragment.getInstance(12));
        fragmentList.add(TestFragment.getInstance(13));
        mainNoTouchViewPager.setOffscreenPageLimit(fragmentList.size());
        navigationController = mainPageBottomTabLayout.custom()
                .addItem(newItem(R.mipmap.empty, R.mipmap.error, "首页"))
                .addItem(newItem(R.mipmap.empty, R.mipmap.error, "首页"))
                .addItem(newItem(R.mipmap.empty, R.mipmap.error, "首页"))
                .addItem(newItem(R.mipmap.empty, R.mipmap.error, "首页"))
                .build();
        mainNoTouchViewPager.setAdapter(new FragmentViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        navigationController.setupWithViewPager(mainNoTouchViewPager);
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    private void initView() {

    }

    //创建一个Item
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(ContextCompat.getColor(this, R.color.colorAccent));
        normalItemView.setTextCheckedColor(ContextCompat.getColor(this, R.color.colorActionSheetCancelText));
        return normalItemView;
    }

}
