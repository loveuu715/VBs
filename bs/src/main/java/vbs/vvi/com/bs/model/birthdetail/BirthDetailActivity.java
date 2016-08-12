package vbs.vvi.com.bs.model.birthdetail;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseActivity;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.utils.ImageLoader;
import vbs.vvi.com.bs.utils.ThreadUtil;

/**
 * 生日详情
 * Created by Wayne on 2016/8/12.
 * Email: loveuu715@163.com
 */
public class BirthDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_birth_detail_toolbar_fbg)
    ImageView mFbg;
    @BindView(R.id.iv_birth_detail_toolbar_bg)
    ImageView mBg;


    DBUserBean mBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_birth_detail;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("有你");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        mBean = (DBUserBean) getIntent().getSerializableExtra("birthBean");
        if (mBean != null) {
            mToolbar.setTitle(mBean.getName());
            ImageLoader.display(mContext, mFbg, mBean.getAvatarPath());
            ThreadUtil.safeRun(new Runnable() {
                @Override
                public void run() {
                    ImageLoader.displayWithBlur(mContext, mBg, mBean.getAvatarPath(), 15);
                }
            });
        }
    }
}
