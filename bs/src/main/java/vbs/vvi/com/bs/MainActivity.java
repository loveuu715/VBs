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
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vbs.vvi.com.bs.base.BaseActivity;
import vbs.vvi.com.bs.base.SpacesItemDecoration;
import vbs.vvi.com.bs.common.events.EventIds;
import vbs.vvi.com.bs.common.events.EventObject;
import vbs.vvi.com.bs.db.DBManager;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.model.addbirth.AddBirthActivity;
import vbs.vvi.com.bs.model.mainlist.MainListFragment;
import vbs.vvi.com.bs.utils.ImageLoader;
import vbs.vvi.com.bs.utils.SceneManager;
import vbs.vvi.com.bs.utils.ThreadUtil;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_main_blur_bg)
    ImageView mBlurBg;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.rl_empty_view_root)
    ScrollView mEmptyView;
    @BindView(R.id.fl_main_list)
    FrameLayout mContainer;
    @BindView(R.id.iv_main_empty_view_gif)
    ImageView mIvGif;


    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;
    private SpacesItemDecoration itemDecoration;
    private MainListFragment mMainListFragment;
    private FragmentManager mFragmentManager;

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
                SceneManager.toScene(mContext, AddBirthActivity.class, null);
//                SceneManager.toScene(mContext, ContactListActivity.class, null);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        ThreadUtil.safeRun(new Runnable() {
            @Override
            public void run() {
                ImageLoader.displayWithBlur(mContext, mBlurBg, R.mipmap.default_blur_bg);
            }
        });
        //显示虚化背景
    }


    @Override
    public void initData() {
        initListDatas();
    }

    private void initListDatas() {
        List<DBUserBean> datas = getDbDatas();
        if (datas.size() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
            if (mMainListFragment != null) {
                mMainListFragment.onDestroy();
            }
//            ImageLoader.display(mContext, mIvGif, R.mipmap.empty_gif);
            return;
        }
        mEmptyView.setVisibility(View.GONE);
        if (mMainListFragment == null) {
            mMainListFragment = new MainListFragment(mContext);
            mFragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fl_main_list, mMainListFragment);
            fragmentTransaction.commit();
        } else {
            mMainListFragment.refreshDatas(datas);
        }
    }

    private List<DBUserBean> getDbDatas() {
        return DBManager.getInstance(BaseApplication.getApplication()).queryOffset(1);
    }

    @Override
    public void onEvent(EventObject eo) {
        super.onEvent(eo);
        if (eo.getEventId() == EventIds.ID.UPDATE_MAIN_USER_INFO) {
            initListDatas();
        }
    }

    @OnClick({R.id.civ_user_avatar, R.id.cl_main_menu_rootView, R.id.tv_main_empty_view_reload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cl_main_menu_rootView:
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.civ_user_avatar:
                mDrawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.tv_main_empty_view_reload:
                initListDatas();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.finishAllActivities();
//        System.exit(0);
    }
}
