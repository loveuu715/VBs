package vbs.vvi.com.bs;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vbs.vvi.com.bs.base.BaseActivity;
import vbs.vvi.com.bs.base.SpacesItemDecoration;
import vbs.vvi.com.bs.db.DBManager;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.model.adduser.AddUserActivity;
import vbs.vvi.com.bs.model.mainlist.MainListAdapter;
import vbs.vvi.com.bs.model.mainlist.MainListFragment;
import vbs.vvi.com.bs.utils.ImageLoader;
import vbs.vvi.com.bs.utils.SceneManager;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_main_blur_bg)
    ImageView mBlurBg;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;


    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;
    private MainListAdapter mMainListAdapter;
    private SpacesItemDecoration itemDecoration;
    private MainListFragment mMainListFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setTitle(R.string.title_main);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SceneManager.toScene(mContext, AddUserActivity.class, null);
//                SceneManager.toScene(mContext, ContactListActivity.class, null);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //显示虚化背景
        ImageLoader.displayWithBlur(this, mBlurBg, R.mipmap.default_blur_bg);
        initListDatas();
    }

    private void initListDatas() {
        List<DBUserBean> datas = getDbDatas();
        mMainListFragment = new MainListFragment(datas, mContext);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main_list, mMainListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void initData() {
        initDbDatas();
    }


    private void initDbDatas() {
        List<DBUserBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new DBUserBean(100 + i, "100" + i, 0, "兮夜" + i, "18576631715", System.currentTimeMillis(), 1, 0, 0, "无", System.currentTimeMillis(), "无地址", null, 1, 0, "暂无分组"));
        }
        DBManager.getInstance(BaseApplication.getApplication()).insertUserList(list);
    }

    private List<DBUserBean> getDbDatas() {
        return DBManager.getInstance(BaseApplication.getApplication()).queryUserList();
    }

    @OnClick({R.id.civ_user_avatar, R.id.cl_main_menu_rootView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cl_main_menu_rootView:
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.civ_user_avatar:
                mDrawer.closeDrawer(GravityCompat.START);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
