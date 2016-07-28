package vbs.vvi.com.bs.model.mainlist;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.listener.RcvOnItemClickListener;
import vbs.vvi.com.bs.listener.RcvOnItemLongClickListener;
import vbs.vvi.com.bs.ui.CircleImageView;
import vbs.vvi.com.bs.utils.ImageLoader;

/**
 * Created by Wayne on 2016/7/26.
 * Email: loveuu715@163.com
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListViewHolder> {

    private Context mContext;
    private List<DBUserBean> mDatas;
    private RcvOnItemClickListener mRcvOnItemClickListener;
    private RcvOnItemLongClickListener mRcvOnItemLongClickListener;

    public MainListAdapter(Context context, List<DBUserBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public MainListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return new MainListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_list, parent, false));
        return new MainListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_list_l, parent, false));
    }

    @Override
    public void onBindViewHolder(final MainListViewHolder holder, int position) {
        ImageLoader.display(mContext, holder.ivAvatar, R.mipmap.default_list_avatar);
        holder.tvName.setText(mDatas.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRcvOnItemClickListener != null)
                    mRcvOnItemClickListener.onItemClickListener(holder.itemView, holder.getLayoutPosition());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mRcvOnItemLongClickListener != null)
                    mRcvOnItemLongClickListener.onItemLongClickListener(holder.itemView, holder.getLayoutPosition());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class MainListViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private TextView tvName;
        private ImageView ivGender;
        private TextView tvRemark;

        public MainListViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_mainList_name);
            ivAvatar = (CircleImageView) itemView.findViewById(R.id.civ_mainList_avatar);
        }
    }

    public void setOnItemClickListener(RcvOnItemClickListener onItemClickListener) {
        this.mRcvOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(RcvOnItemLongClickListener onItemLongClickListener) {
        this.mRcvOnItemLongClickListener = onItemLongClickListener;
    }
}
