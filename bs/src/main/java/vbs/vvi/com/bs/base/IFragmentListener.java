package vbs.vvi.com.bs.base;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Wayne on 2016/7/28.
 * Email: loveuu715@163.com
 */
public interface IFragmentListener {
    void onRefresh(SwipeRefreshLayout refreshLayout);
    void loadMore();
}
