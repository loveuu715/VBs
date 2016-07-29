package vbs.vvi.com.bs.model.contact;

import android.content.Context;
import android.view.View;

import org.byteam.superadapter.recycler.BaseViewHolder;
import org.byteam.superadapter.recycler.IMultiItemViewType;
import org.byteam.superadapter.recycler.SuperAdapter;

import java.util.List;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.bean.ContactBean;
import vbs.vvi.com.bs.ui.CircleImageView;
import vbs.vvi.com.bs.utils.ToastUtil;

/**
 * Created by Wayne on 2016/7/29.
 * Email: loveuu715@163.com
 */
public class ContactListAdapter extends SuperAdapter<ContactBean> {

    public ContactListAdapter(Context context, List<ContactBean> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    public ContactListAdapter(Context context, List<ContactBean> items, IMultiItemViewType<ContactBean> multiItemViewType) {
        super(context, items, multiItemViewType);
    }

    @Override
    public void onBind(int viewType, BaseViewHolder holder, int position, final ContactBean item) {
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show(getContext(),"点击了联系人:"+item.getName(),0);
            }
        });

        CircleImageView civAvatar = holder.getView(R.id.iv_item_contact_list_avatar);
        civAvatar.setImageBitmap(item.getContactAvatar());
        holder.setText(R.id.tv_item_contact_list_name,item.getName());
        holder.setText(R.id.tv_item_contact_list_phoneNum,item.getPhoneNum());
    }
}
