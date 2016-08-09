package vbs.vvi.com.bs.model.mainlist;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.byteam.superadapter.recycler.OnItemClickListener;
import org.byteam.superadapter.recycler.SuperAdapter;

import java.util.ArrayList;
import java.util.List;

import vbs.vvi.com.bs.BaseApplication;
import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseRefreshFragment;
import vbs.vvi.com.bs.db.DBManager;
import vbs.vvi.com.bs.db.DBUserBean;

/**
 * Created by Wayne on 2016/8/4.
 * Email: loveuu715@163.com
 */
public class MainListFragment extends BaseRefreshFragment {

    private List<DBUserBean> mDatas = new ArrayList<>();
    private Context mContext;
    private MainAdapter mMainAdapter;

    public MainListFragment(Context context) {
        mContext = context;
    }

    @Override
    protected void refreshData(final SwipeRefreshLayout refreshLayout) {
        pageNo = 1;
        refreshDatas(DBManager.getInstance(BaseApplication.getApplication()).queryOffset(pageNo));
        if (refreshLayout != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(false);
                }
            }, 1000);
        }
    }

    @Override
    protected void loadData(final XRecyclerView recycleView) {
        pageNo++;
        loadMore(DBManager.getInstance(BaseApplication.getApplication()).queryOffset(pageNo));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (recycleView != null)
                    recycleView.loadMoreComplete();
            }
        }, 1000);
    }

    @Override
    protected SuperAdapter getAdapter() {
        mMainAdapter = new MainAdapter(mContext, mDatas, R.layout.item_main_list_l);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            mMainAdapter = new MainAdapter(mContext, mDatas, R.layout.item_main_list);
        mMainAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                //TODO 生日详情
            }
        });
        return mMainAdapter;
    }

    public void refreshDatas(List<DBUserBean> datas) {
        pageNo = 1;
        if (mMainAdapter != null) {
            mMainAdapter.getList().clear();
            mMainAdapter.addAll(datas);
            mMainAdapter.notifyDataSetChanged();
            recycleView.smoothScrollToPosition(0);
        }
    }

    public void loadMore(List<DBUserBean> datas) {
        if (mMainAdapter != null) {
            mMainAdapter.addAll(datas);
            mMainAdapter.notifyDataSetChanged();
        }
    }
}
