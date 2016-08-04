package vbs.vvi.com.bs.model.adduser;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseActivity;
import vbs.vvi.com.bs.ui.CircleImageView;
import vbs.vvi.com.bs.utils.ImageLoader;

/**
 * Created by Wayne on 2016/8/3.
 * Email: loveuu715@163.com
 */
public class AddUserActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab_add_user)
    FloatingActionButton mActionButton;
    @BindView(R.id.iv_add_user_avatar_blur_bg)
    ImageView mImageViewBg;
    @BindView(R.id.civ_add_user_avatar)
    CircleImageView mCivAvatar;



    @Override
    public int getLayoutId() {
        return R.layout.activity_add_user;
    }

    @Override
    public void initView() {
        mToolbar.setTitle(getString(R.string.title_add_user));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ImageLoader.displayWithBlur(mContext, mImageViewBg, R.mipmap.default_avatar);
    }

    @Override
    public void initData() {

    }

}
