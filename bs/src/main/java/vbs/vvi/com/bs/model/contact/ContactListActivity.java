package vbs.vvi.com.bs.model.contact;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.byteam.superadapter.recycler.SuperAdapter;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseRefreshActivity;
import vbs.vvi.com.bs.utils.PhoneUtil;

/**
 * Created by Wayne on 2016/7/29.
 * Email: loveuu715@163.com
 */
public class ContactListActivity extends BaseRefreshActivity {

    @Override
    public void initToolbar() {
        mToolbar.setTitle("联系人列表");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected SuperAdapter getAdapter() {
        return new ContactListAdapter(mContext, PhoneUtil.getPhoneContacts(mContext), R.layout.item_contact_list);
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
    protected void loadData(XRecyclerView recycleView) {
        recycleView.loadMoreComplete();
    }
}
