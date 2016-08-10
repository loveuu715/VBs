package vbs.vvi.com.bs.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import java.util.Calendar;

import vbs.vvi.com.bs.R;
import vbs.vvi.com.bs.ui.dialog.SlideUpDialog;
import vbs.vvi.com.bs.ui.lunarcalendar.GregorianLunarCalendarView;

/**
 * Created by Wayne on 2016/8/8.
 * Email: loveuu715@163.com
 */
public class DialogManager {

    private static GregorianLunarCalendarView sMCalendarView;
    private static SlideUpDialog sDateDialog;
    private static SlideUpDialog sGenderDialog;
    private static int sDateType;
    private static SlideUpDialog sPhotoDialog;
    private static TabLayout sTabLayout;

    public interface OnDateSelectListener {
        void onTabClick(SlideUpDialog slideUpDialog, TabLayout.Tab tab);

        void onDateFinish(SlideUpDialog slideUpDialog, Calendar calendar, int type);

        void onCancel(SlideUpDialog slideUpDialog);
    }

    public interface OnGenderSelectListener {
        void onGenderConfirm(SlideUpDialog slideUpDialog, int position);
    }

    public interface OnPhotoDialogListener {
        void onCancel(SlideUpDialog dialog);

        void onTakePhoto(SlideUpDialog dialog);

        void onGalleryPhoto(SlideUpDialog dialog);
    }

    public static SlideUpDialog showSelectDateDailog(final Context context, final Calendar calendar, final boolean isLruaCalendar, final OnDateSelectListener listener) {
        sDateDialog = new SlideUpDialog(context).bindView(R.layout.dialog_select_date, new SlideUpDialog.BindViewListener() {
            @Override
            public void onBind(View contentView) {
                initCalendarView(sDateDialog, contentView, calendar, isLruaCalendar, listener);
            }
        });
        sDateDialog.show();
        return sDateDialog;
    }

    private static void initCalendarView(final SlideUpDialog slideUpDialog, View contentView, Calendar calendar, boolean isLruaCalendar, final OnDateSelectListener listener) {
        sMCalendarView = (GregorianLunarCalendarView) contentView.findViewById(R.id.glcv_addUser_select_date);
        sTabLayout = (TabLayout) contentView.findViewById(R.id.tl_dialog_select_date);
        sTabLayout.addTab(sTabLayout.newTab().setText("公历"));
        if (isLruaCalendar) {
            sTabLayout.addTab(sTabLayout.newTab().setText("农历"), 1, true);
        } else {
            sTabLayout.addTab(sTabLayout.newTab().setText("农历"));
        }
        sTabLayout.setSelectedTabIndicatorHeight(2);
        sTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (listener != null)
                    listener.onTabClick(sDateDialog, tab);
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
        LogUtil.i("hate", "传入时间:" + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        sMCalendarView.init(calendar);

        if (isLruaCalendar) {
            ThreadUtil.safeRunDelay(new Runnable() {
                @Override
                public void run() {
                    sMCalendarView.toLunarMode();
                }
            }, 30);
        }

        contentView.findViewById(R.id.tv_dialog_select_date_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onCancel(sDateDialog);
            }
        });

        contentView.findViewById(R.id.tv_dialog_select_date_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = sMCalendarView.getCalendarData().getCalendar();//返回的是ChineseCalendar对象
                if (listener != null)
                    listener.onDateFinish(sDateDialog, calendar, sDateType);
            }
        });
    }


    public static SlideUpDialog showSelectGenderDialog(Context context, final int position, final OnGenderSelectListener onGenderSelectListener) {
        sGenderDialog = new SlideUpDialog(context).bindView(R.layout.dialog_select_gender, new SlideUpDialog.BindViewListener() {
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
                        onGenderSelectListener.onGenderConfirm(sGenderDialog, 0);
                        cbMale.setChecked(true);
                        cbFemale.setChecked(false);
                    }
                });
                femaleRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onGenderSelectListener.onGenderConfirm(sGenderDialog, 1);
                        cbFemale.setChecked(true);
                        cbMale.setChecked(false);
                    }
                });
            }
        });
        sGenderDialog.show();
        return sGenderDialog;
    }

    public static SlideUpDialog showSelectPhoto(Context context, final OnPhotoDialogListener listener) {
        sPhotoDialog = new SlideUpDialog(context);
        sPhotoDialog.bindView(R.layout.dialog_select_photo, new SlideUpDialog.BindViewListener() {
            @Override
            public void onBind(View contentView) {
                contentView.findViewById(R.id.photograph).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onTakePhoto(sPhotoDialog);
                        }
                    }
                });
                contentView.findViewById(R.id.from_pic_select).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onGalleryPhoto(sPhotoDialog);
                        }
                    }
                });
                contentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onCancel(sPhotoDialog);
                        }
                    }
                });
            }
        });
        sPhotoDialog.show();
        return sPhotoDialog;
    }


}
