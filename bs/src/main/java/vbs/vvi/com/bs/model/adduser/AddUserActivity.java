package vbs.vvi.com.bs.model.adduser;

import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import vbs.vvi.com.bs.BaseApplication;
import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.base.BaseActivity;
import vbs.vvi.com.bs.common.events.EventIds;
import vbs.vvi.com.bs.common.events.EventObject;
import vbs.vvi.com.bs.db.DBManager;
import vbs.vvi.com.bs.db.DBUserBean;
import vbs.vvi.com.bs.ui.CircleImageView;
import vbs.vvi.com.bs.ui.SlideUpDialog;
import vbs.vvi.com.bs.ui.edittext.EditTextX;
import vbs.vvi.com.bs.ui.lunarcalendar.ChineseCalendar;
import vbs.vvi.com.bs.utils.DateUtils;
import vbs.vvi.com.bs.utils.DialogManager;
import vbs.vvi.com.bs.utils.ImageLoader;
import vbs.vvi.com.bs.utils.ThreadUtil;
import vbs.vvi.com.bs.utils.TipUtil;
import vbs.vvi.com.bs.utils.UUIDGenerator;

/**
 * Created by Wayne on 2016/8/3.
 * Email: loveuu715@163.com
 */
public class AddUserActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab_add_user)
    FloatingActionButton mActionButton;
    @BindView(R.id.iv_add_user_avatar_blur_bg)
    ImageView mImageViewBg;
    @BindView(R.id.civ_add_user_avatar)
    CircleImageView mCivAvatar;
    @BindView(R.id.edt_addUser_name)
    EditTextX mName;
    @BindView(R.id.tv_addUser_gender)
    TextView mGender;
    @BindView(R.id.tv_addUser_birthday)
    TextView mBirthday;
    @BindView(R.id.edt_addUser_phone)
    EditTextX mPhone;
    @BindView(R.id.edt_addUser_remark)
    EditTextX mRemark;
    private Calendar mCalendar;
    private int mPosition;
    private int mGenderPos;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mBirthType;
    private int mLYear;
    private int mLMonth;
    private int mLDay;


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_user;
    }

    @Override
    public void initView() {
        mToolbar.setTitle(getString(R.string.title_add_user));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ThreadUtil.safeRun(new Runnable() {
            @Override
            public void run() {
                ImageLoader.displayWithBlur(mContext, mImageViewBg, R.mipmap.default_avatar);
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.civ_add_user_avatar, R.id.tv_addUser_gender, R.id.tv_addUser_birthday})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_add_user_avatar:
                break;
            case R.id.tv_addUser_gender:
                showGenderDialog();
                break;
            case R.id.tv_addUser_birthday:
                showBirthdayDialog();
                break;
        }
    }

    private void showGenderDialog() {
        DialogManager.showSelectGenderDialog(mContext, mGenderPos, new DialogManager.OnGenderSelectListener() {
            @Override
            public void onGenderCancel(SlideUpDialog slideUpDialog) {
                slideUpDialog.dismiss();
            }

            @Override
            public void onGenderConfirm(SlideUpDialog slideUpDialog, int position) {
                mGenderPos = position;
                if (position == 0) {
                    mGender.setText("男");
                } else {
                    mGender.setText("女");
                }
                slideUpDialog.dismiss();
            }
        });
    }

    private void showBirthdayDialog() {
        if (mYear != 0) {
            mCalendar = Calendar.getInstance();
            mCalendar.set(mYear, mMonth - 1, mDay);
        }

        DialogManager.showSelectDateDailog(mContext, mCalendar, mBirthType == 0 ? false : true, new DialogManager.OnDateSelectListener() {
            @Override
            public void onTabClick(SlideUpDialog slideUpDialog, TabLayout.Tab tab) {
            }

            @Override
            public void onDateFinish(SlideUpDialog slideUpDialog, Calendar calendar, int type) {
                mBirthType = type;
                mYear = calendar.get(Calendar.YEAR);//获取公历年 2016
                mMonth = calendar.get(Calendar.MONTH) + 1;//获取公历月 6
                mDay = calendar.get(Calendar.DAY_OF_MONTH);//获取公历日 20

                mLYear = calendar.get(ChineseCalendar.CHINESE_YEAR);//获取农历年 2016
                mLMonth = calendar.get(ChineseCalendar.CHINESE_MONTH);//获取农历月 5//注意，如果是闰五月则返回-5
                mLDay = calendar.get(ChineseCalendar.CHINESE_DATE);//获取农历日 20

                if (mBirthType == 0) {//公历
                    mBirthday.setText(mYear + "年 " + mMonth + "月 " + mDay + "日");
                } else {//农历
                    String tip = mLYear + "年 " + mLMonth + "月 " + mLDay + "日";
                    if (mMonth < 0) {
                        tip = mLYear + "年 润" + Math.abs(mLMonth) + "月 " + mLDay + "日";
                    }
                    mBirthday.setText(tip);
                }
                slideUpDialog.dismiss();
            }

            @Override
            public void onCancel(SlideUpDialog slideUpDialog) {
                slideUpDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.submit_user) {
            String name = mName.getText().toString().trim();
            String birth = mBirthday.getText().toString().trim();
            String phone = mPhone.getText().toString().trim();
            String remark = mRemark.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                TipUtil.showSnackbar(mActionButton, "请填写姓名");
                mName.setShakeAnimation();
                return true;
            }

            if ("请选择生日".equals(birth) || TextUtils.isEmpty(birth)) {
                TipUtil.showSnackbar(mActionButton, "请选择生日");
                return true;
            }

            if (!TextUtils.isEmpty(phone)) {
                if (phone.length() != 11) {
                    TipUtil.showSnackbar(mActionButton, "手机号格式错误");
                    mPhone.setShakeAnimation();
                    return true;
                }
                //手机号正则
                String phoneRegex = "[1][34578]\\d{9}";
                if (!phone.matches(phoneRegex)) {
                    mPhone.setShakeAnimation();
                    TipUtil.showSnackbar(mActionButton, "手机号格式错误");
                    return true;
                }
            }

            DBUserBean userBean = new DBUserBean();
            userBean.setUserId(System.currentTimeMillis() / 100);//时间戳标记
            userBean.setUserKey(UUIDGenerator.getUUID());//唯一标示
            userBean.setCreateTime(System.currentTimeMillis());//创建时间
            userBean.setName(name);//姓名
            userBean.setPhone(phone);//手机
            userBean.setBirthday(DateUtils.str2Date(mYear + "-" + mMonth + "-" + mDay, DateUtils.FORMAT_YMD).getTime());//生日时间戳
            userBean.setTipSetting(0);//提醒类型 待定
            userBean.setGender(mGenderPos);//性别 0 男 1 女
            userBean.setImportant(0);//是否重点关注 0 否 1 是
            userBean.setRemark(remark);//备注
            userBean.setAddress("无");//地址
            userBean.setAvatarPath("");//头像地址
            userBean.setRelationship(0);//关系类型 待定
            userBean.setGroupInfo(0);//分组类型 待定
            userBean.setGroupName("无");//组名
            userBean.setYear(mYear);//生日年份
            userBean.setMonth(mMonth);//生日月份
            userBean.setDay(mDay);//生日日期
            userBean.setBirthType(mBirthType);//生日日期类型 0 公历 1农历

            DBManager.getInstance(mContext).insertUser(userBean);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new EventObject(EventIds.ID.UPDATE_MAIN_USER_INFO, null));
                }
            }, 30);
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
