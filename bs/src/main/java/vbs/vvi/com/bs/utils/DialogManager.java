package vbs.vvi.com.bs.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import java.util.Calendar;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.ui.SlideUpDialog;
import vbs.vvi.com.bs.ui.lunarcalendar.GregorianLunarCalendarView;

/**
 * Created by Wayne on 2016/8/8.
 * Email: loveuu715@163.com
 */
public class DialogManager {

    private static GregorianLunarCalendarView sMCalendarView;
    private static SlideUpDialog sMSlideUpDialog;
    private static SlideUpDialog sSlideUpDialog;
    private static int sGenderPosition;
    private static int sDateType;

    public interface OnDateSelectListener {
        void onTabClick(SlideUpDialog slideUpDialog, TabLayout.Tab tab);

        void onDateFinish(SlideUpDialog slideUpDialog, Calendar calendar, int type);

        void onCancel(SlideUpDialog slideUpDialog);
    }

    public interface OnGenderSelectListener {
        void onGenderCancel(SlideUpDialog slideUpDialog);

        void onGenderConfirm(SlideUpDialog slideUpDialog, int position);
    }

    public static SlideUpDialog showSelectDateDailog(final Context context, final Calendar calendar, final boolean isLruaCalendar, final OnDateSelectListener listener) {
        sMSlideUpDialog = new SlideUpDialog(context).bindView(R.layout.dialog_select_date, new SlideUpDialog.BindViewListener() {
            @Override
            public void onBind(View contentView) {
                initCalendarView(sMSlideUpDialog, contentView, calendar, isLruaCalendar, listener);
            }
        });
        sMSlideUpDialog.show();
        return sMSlideUpDialog;
    }

    private static void initCalendarView(final SlideUpDialog slideUpDialog, View contentView, Calendar calendar, boolean isLruaCalendar, final OnDateSelectListener listener) {
        sMCalendarView = (GregorianLunarCalendarView) contentView.findViewById(R.id.glcv_addUser_select_date);
        TabLayout tabLayout = (TabLayout) contentView.findViewById(R.id.tl_dialog_select_date);
        tabLayout.addTab(tabLayout.newTab().setText("公历"));
        tabLayout.addTab(tabLayout.newTab().setText("农历"));
        tabLayout.setSelectedTabIndicatorHeight(2);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (listener != null)
                    listener.onTabClick(sMSlideUpDialog, tab);
                sDateType = tab.getPosition();
                if (sDateType == 0) {
                    sMCalendarView.toGregorianMode();
                } else if (sDateType == 1) {
                    sMCalendarView.toLunarMode();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (calendar == null) {
            calendar = Calendar.getInstance();
            calendar.set(2000, 04, 20);//2000-05-20
        }
        sMCalendarView.init(calendar);

        if (isLruaCalendar) {
            tabLayout.setVerticalScrollbarPosition(1);
//            tabLayout.setScrollPosition(1, 1, true);
            sMCalendarView.toLunarMode();
        }

        contentView.findViewById(R.id.tv_dialog_select_date_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onCancel(sMSlideUpDialog);
//                slideUpDialog.dismiss();
            }
        });

        contentView.findViewById(R.id.tv_dialog_select_date_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = sMCalendarView.getCalendarData().getCalendar();//返回的是ChineseCalendar对象
                if (listener != null)
                    listener.onDateFinish(sMSlideUpDialog, calendar, sDateType);
            }
        });
    }


    public static SlideUpDialog showSelectGenderDialog(Context context, final int position, final OnGenderSelectListener onGenderSelectListener) {
        sGenderPosition = position;
        sSlideUpDialog = new SlideUpDialog(context).bindView(R.layout.dialog_select_gender, new SlideUpDialog.BindViewListener() {
            @Override
            public void onBind(View contentView) {
                final CheckBox cbMale = (CheckBox) contentView.findViewById(R.id.cb_dialog_select_gender_male);
                final CheckBox cbFemale = (CheckBox) contentView.findViewById(R.id.cb_dialog_select_gender_female);
                if (position == 0) {
                    cbMale.setChecked(true);
                    cbFemale.setChecked(false);
                } else {
                    cbMale.setChecked(false);
                    cbFemale.setChecked(true);
                }
                final RelativeLayout maleRootView = (RelativeLayout) contentView.findViewById(R.id.rl_dialog_select_gender_male_root);
                RelativeLayout femaleRootView = (RelativeLayout) contentView.findViewById(R.id.rl_dialog_select_gender_female_root);
                maleRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!cbMale.isChecked()) {
                            cbMale.setChecked(true);
                        }
                        cbFemale.setChecked(false);
                        sGenderPosition = 0;
                    }
                });
                femaleRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!cbFemale.isChecked()) {
                            cbFemale.setChecked(true);
                        }
                        cbMale.setChecked(false);
                        sGenderPosition = 1;
                    }
                });
                //取消
                contentView.findViewById(R.id.fl_dialog_select_gender_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onGenderSelectListener.onGenderCancel(sSlideUpDialog);
                    }
                });
                //确定
                contentView.findViewById(R.id.fl_dialog_select_gender_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onGenderSelectListener != null)
                            onGenderSelectListener.onGenderConfirm(sSlideUpDialog, sGenderPosition);
                    }
                });
            }
        });
        sSlideUpDialog.show();
        return sSlideUpDialog;
    }
}
