package vbs.vvi.com.bs.view;

import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseActivity;

/**
 * Created by Wayne on 2016/7/20.
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("设置");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_getDatas})
    public void onClick(View view) {

    }
}
