package vbs.vvi.com.bs.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vbs.vvi.com.bs.BaseApplication;
import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.common.events.EventObject;

/**
 * Created by Wayne on 2016/7/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        BaseApplication.activityEnqueue(this);
        initBundle();
        initView();
        initData();
    }

    public void initBundle() {}

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public void setTransparantBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void setFullScreen() {
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Subscribe
    public void onEvent(EventObject eo) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.b_exit_anim);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
