package vbs.vvi.com.bs.view;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
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
        OkHttpUtils.get().url("http://www.baidu.com").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.i("HATE", response);
            }
        });
    }
}
