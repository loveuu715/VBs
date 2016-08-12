package vbs.vvi.com.bs.model.mainlist;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import org.byteam.superadapter.recycler.BaseViewHolder;
import org.byteam.superadapter.recycler.IMultiItemViewType;
import org.byteam.superadapter.recycler.SuperAdapter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.ui.CircleImageView;
import vbs.vvi.com.bs.utils.DateUtils;
import vbs.vvi.com.bs.utils.ImageLoader;

/**
 * Created by Wayne on 2016/8/4.
 * Email: loveuu715@163.com
 */
public class MainAdapter extends SuperAdapter<DBUserBean> {

    private int currentYear;
    private int currentMonth;
    private int currentDay;

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
        ImageLoader.display(mContext, ivAvatar, item.getAvatarPath());
        ImageView ivGender = holder.getView(R.id.iv_main_list_gender);
        ImageLoader.display(mContext, ivGender, item.getGender() == 0 ? R.mipmap.male : R.mipmap.female);
        TextView tvBirth = holder.getView(R.id.tv_mainlist_birthday);
        ImageView ivBirthType = holder.getView(R.id.iv_main_list_birthType);
        if (item.getBirthType() == 1) {//农历
            String tip = item.getLMonth() + "月" + item.getLDay() + "日";
            if (item.getLMonth() < 0)
                tip = "润" + Math.abs(item.getLMonth()) + "月" + item.getLDay() + "日";
            tvBirth.setText(tip);
            ivBirthType.setBackgroundResource(R.drawable.lunar_icon);
        } else {//公历
            tvBirth.setText(item.getMonth() + "月" + item.getDay() + "日");
            ivBirthType.setBackgroundResource(R.drawable.solar_icon);
        }
        int[] yd = getDays(item);
        TextView tvDays = holder.getView(R.id.tv_mainlist_days);
        tvDays.setText(yd[1] + "天");
        TextView tvTip = holder.getView(R.id.tv_mainlist_birTip);
        tvTip.setText("后过" + yd[0] + "岁生日");
    }

    private void getCurrentDayDetails() {
        Calendar calendar = Calendar.getInstance();
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int[] getDays(DBUserBean item) {
        getCurrentDayDetails();
        int year = item.getYear();
        int month = item.getMonth();
        int day = item.getDay();

        int lYear = item.getLYear();
        int lMonth = item.getLMonth();
        int lDay = item.getLDay();

        int birthY = 0;

        Date from;
        Date to;
        int myDays = 0;
        birthY = currentYear - year;
        if (month > currentMonth) {
            from = DateUtils.getDate(currentYear + "-" + currentMonth + "-" + currentDay);
            to = DateUtils.getDate(currentYear + "-" + month + "-" + day);
            myDays = DateUtils.getGapCount(from, to);
        } else if (month < currentMonth) {
            from = DateUtils.getDate(currentYear + "-" + currentMonth + "-" + currentDay);
            to = DateUtils.getDate((currentYear + 1) + "-" + month + "-" + day);
            myDays = DateUtils.getGapCount(from, to);
            birthY++;
        } else {
            if (day > currentDay) {
                from = DateUtils.getDate(currentYear + "-" + currentMonth + "-" + currentDay);
                to = DateUtils.getDate(currentYear + "-" + month + "-" + day);
                myDays = DateUtils.getGapCount(from, to);
            } else if (day < currentDay) {
                from = DateUtils.getDate(currentYear + "-" + currentMonth + "-" + currentDay);
                to = DateUtils.getDate((currentYear + 1) + "-" + month + "-" + day);
                myDays = DateUtils.getGapCount(from, to);
                birthY++;
            }
        }
        return new int[]{birthY, myDays};
    }

    /**
     * 计算农历天数
     *
     * @return
     */
    private int[] getLDays(DBUserBean item) {
        int year = item.getLYear();
        int month = item.getLMonth();
        int day = item.getLDay();
        getCurrentLDate(year, month, day);
        return new int[]{};
    }

    private void getCurrentLDate(int year, int month, int day) {

    }

    public DBUserBean getBean(int position) {
        return mList.get(position - 1);
    }
}
