package vbs.vvi.com.bs.model.mainlist;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.byteam.superadapter.recycler.OnItemClickListener;
import org.byteam.superadapter.recycler.SuperAdapter;

import java.util.List;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseRefreshFragment;
import vbs.vvi.com.bs.db.DBUserBean;

/**
 * Created by Wayne on 2016/8/4.
 * Email: loveuu715@163.com
 */
public class MainListFragment extends BaseRefreshFragment {

    private List<DBUserBean> mDatas;
    private Context mContext;

    public MainListFragment(List<DBUserBean> datas, Context context) {
        mDatas = datas;
        mContext = context;
    }

    @Override
    protected void refreshData(final SwipeRefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout != null)
                    refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    protected void loadData(final XRecyclerView recycleView) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (recycleView != null)
                    recycleView.loadMoreComplete();
            }
        }, 2000);
    }

    @Override
    protected SuperAdapter getAdapter() {
        MainAdapter mainAdapter = new MainAdapter(mContext, mDatas, R.layout.item_main_list_l);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            mainAdapter = new MainAdapter(mContext, mDatas, R.layout.item_main_list);
        mainAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                //TODO 生日详情
            }
        });
        return mainAdapter;
    }
}
