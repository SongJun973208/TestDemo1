package org.sj.testdemo.zxing;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.aries.ui.view.title.TitleBarView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created: AriesHoo on 2017/7/3 16:04
 * Function: title 基类
 * Desc:
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected TitleBarView titleBar;
    protected Activity mContext;
    protected boolean mIsFirstShow = true;
    private Unbinder mUnBinder;
    protected int type = 0;
    protected View mContentView;

    private Toast toast;
    private boolean isDispatchTouchEvent = true;

    protected abstract void setTitleBar();

    protected boolean isShowLine() {
        return false;
    }

    @LayoutRes
    protected abstract int getLayout();

    protected void loadData() {
    }

    protected void beforeSetView() {
    }

    protected void beforeInitTitle() {
    }

    protected void beforeInitView() {
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
    }

//    @Subscribe
//    public void onAppEvent(AppEvent appEvent) {
//
//    }

    protected abstract void initView(Bundle var1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        this.mContext = this;
//        isLogin();
//        FastStackUtil.getInstance().push(this);
        this.beforeSetView();
        mContentView = View.inflate(mContext, getLayout(), null);
        this.setContentView(mContentView);
        mUnBinder = ButterKnife.bind(this);
//        mSVProgressHUD = new SVProgressHUD(this);
//        AndroidBug5497Workaround.assistActivity(this);
//        isLogin();
        initTitle();
        this.beforeInitView();
        this.initView(savedInstanceState);

    }

    protected void initTitle() {
//        titleBar = (TitleBarView) findViewById(R.id.titleBar);
//        if (titleBar == null) {
//            return;
//        }
//        type = titleBar.getStatusBarModeType();
//        if (type <= 0) {//无法设置白底黑字
//            titleBar.setStatusAlpha(0);//5.0 半透明模式alpha-102
//        }
//        titleBar
//                //.setTitleMainText(mContext.getClass().getSimpleName())
//                .setOnLeftTextClickListener(new NoDoubleClickListener() {
//                    @Override
//                    public void onNoDoubleClick(View view) {
//                        onBackPressed();
//                    }
//                });
//        setTitleLine(isShowLine());
//        setTitleBar();
    }

    public void setTitleLine(boolean enable) {
        titleBar.setDividerVisible(enable);
    }

//    public void startActivity(Activity mContext, Class<? extends Activity> activity, Bundle bundle) {
//        AppUtil.startActivity(mContext, activity, bundle);
////        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
//    }

//    public void startActivity(Class<? extends Activity> activity, Bundle bundle) {
//        startActivity(mContext, activity, bundle);
//    }
//
//    public void startActivity(Class<? extends Activity> activity) {
//        startActivity(activity, null);
//    }
//
//    public void startActivityAnimBottom(Class<? extends Activity> activity) {
//        startActivity(activity, null);
//        overridePendingTransition(R.anim.svslide_in_bottom, R.anim.svfade_out_center);
//    }

//    public View getRootView() {
//        return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
//        if (mSVProgressHUD != null) {
//            if (mSVProgressHUD.isShowing()) mSVProgressHUD.dismiss();
//        }
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
//        if (mUnBinder != null) {
//            mUnBinder.unbind();
//        }
//        super.onDestroy();
//        FastStackUtil.getInstance().pop(this, true);
//        titleBar = null;
//        mContext = null;
//        mUnBinder = null;
//        mContentView = null;
//        toast = null;
//        mSVProgressHUD = null;
    }

    @Override
    protected void onResume() {
        if (this.mIsFirstShow) {
            this.mIsFirstShow = false;
            this.loadData();
        }
        super.onResume();
    }



    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && isDispatchTouchEvent) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

//    public boolean isLogin() {
//        String sessionKey = SPUtils.getInstance(Constants.APP_INFO).getString(Constants.APP_SESSION_KEY, "");
//        if (!StringUtils.isTrimEmpty(sessionKey)) {
//            return true;
//        }
//        return false;
//    }
//
//    public String getSessionKey() {
//        return SPUtils.getInstance(Constants.APP_INFO).getString(Constants.APP_SESSION_KEY, "");
//    }
//
//    public void gotoLogin() {
//        startActivityAnimBottom(LoginActivity.class);
//    }


    public boolean isFinish() {
        boolean a = mContext == null || mContext.isFinishing();
        if (Build.VERSION.SDK_INT < 17) {
            return a;
        } else {
            return a || mContext.isDestroyed() || mContext.getFragmentManager().isDestroyed();
        }
    }

}
