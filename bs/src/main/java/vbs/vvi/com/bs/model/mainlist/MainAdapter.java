package vbs.vvi.com.bs.model.mainlist;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import org.byteam.superadapter.recycler.BaseViewHolder;
import org.byteam.superadapter.recycler.IMultiItemViewType;
import org.byteam.superadapter.recycler.SuperAdapter;

import java.util.List;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.ui.CircleImageView;
import vbs.vvi.com.bs.utils.ImageLoader;

/**
 * Created by Wayne on 2016/8/4.
 * Email: loveuu715@163.com
 */
public class MainAdapter extends SuperAdapter<DBUserBean> {
    public MainAdapter(Context context, List<DBUserBean> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    public MainAdapter(Context context, List<DBUserBean> items, IMultiItemViewType<DBUserBean> multiItemViewType) {
        super(context, items, multiItemViewType);
    }

    @Override
    public void onBind(int viewType, BaseViewHolder holder, int position, DBUserBean item) {
        TextView tvName = holder.getView(R.id.tv_mainList_name);
        tvName.setText(item.getName());
        CircleImageView ivAvatar = holder.getView(R.id.civ_mainList_avatar);
        ImageLoader.display(mContext,ivAvatar, R.mipmap.default_list_avatar);
        ImageView ivGender = holder.getView(R.id.iv_main_list_gender);
        ImageLoader.display(mContext, ivGender, item.getGender() == 0 ? R.mipmap.male : R.mipmap.female);
    }
}
