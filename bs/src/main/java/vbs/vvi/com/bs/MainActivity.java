package vbs.vvi.com.bs;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vbs.vvi.com.bs.base.BaseActivity;
import vbs.vvi.com.bs.db.DBManager;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.listener.RcvOnItemClickListener;
import vbs.vvi.com.bs.listener.RcvOnItemLongClickListener;
import vbs.vvi.com.bs.model.mainlist.MainListAdapter;
import vbs.vvi.com.bs.utils.ImageLoader;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_main_blur_bg)
    ImageView mBlurBg;
    @BindView(R.id.rcv_main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.frl_main_list_refresh)
    SwipeRefreshLayout mRefreshLayout;


    private DrawerLayout mDrawer;
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mToolbar.setTitle(R.string.main_title);
        setSupportActionBar(mToolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        ImageLoader.displayWithBlur(this, mBlurBg, R.mipmap.default_blur_bg);

        mRefreshLayout.setColorSchemeResources(R.color.colorAccent, android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light, android.R.color.holo_purple);
        mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        };
        mRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        //第一次需要手动触发刷新
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                mOnRefreshListener.onRefresh();
            }
        }, 50);
    }

    @Override
    public void initData() {
        initDbDatas();
        List<DBUserBean> datas = getDbDatas();
       /* for (int i= 0;i<10 ; i++){
            datas.add(new UserLocalMainListBean("兮夜"+i,0,null,System.currentTimeMillis(),null));
        }*/
        MainListAdapter mainListAdapter = new MainListAdapter(mContext, datas);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mainListAdapter);
        mainListAdapter.setOnItemClickListener(new RcvOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
//                ToastUtil.show(mContext, "点击了:"+position,0);
            }
        });

        mainListAdapter.setOnItemLongClickListener(new RcvOnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View view, int position) {

            }
        });

    }

    private void initDbDatas() {
//        DBManager.getInstance(BaseApplication.getApplication()).insertUser(new DBUserBean(100,"100", 0, "兮夜", "18576631715", System.currentTimeMillis(), 1, 0, 0, "无", System.currentTimeMillis(), "无地址", null, 1, 0, "暂无分组"));
        List<DBUserBean> list = new ArrayList<>();
//        List<DBUserBean> list = DBManager.getInstance(BaseApplication.getApplication()).queryUserList();
//        if (list != null && list.size() != 0)
//            return;
        for (int i = 0; i < 10; i++) {
            list.add(new DBUserBean(100+i,"100"+i, 0, "兮夜" + i, "18576631715", System.currentTimeMillis(), 1, 0, 0, "无", System.currentTimeMillis(), "无地址", null, 1, 0, "暂无分组"));
        }
        DBManager.getInstance(BaseApplication.getApplication()).insertUserList(list);
    }

    private List<DBUserBean> getDbDatas() {
        return DBManager.getInstance(BaseApplication.getApplication()).queryUserList();
    }

    @OnClick({R.id.civ_user_avatar})
    public void onClick(View view) {
        switch (view.getId()) {
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
