package vbs.vvi.com.bs.ui.dialog;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import vbs.vvi.com.bs.R;

/**
 * Created by Wayne on 2016/8/6.
 * Email: loveuu715@163.com
 */
public class SlideUpDialog extends Dialog {

    private View mContentView;
    private BindViewListener mListtener;

    public interface BindViewListener {
        void onBind(View contentView);
    }

    public SlideUpDialog(Context context) {
        super(context, R.style.full_screen_dialog);
    }

    public SlideUpDialog(Context context, int themeResId) {
        super(context, R.style.full_screen_dialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mContentView != null) {
            setContentView(mContentView);
        }
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);

        setCanceledOnTouchOutside(true);
        getWindow().setGravity(Gravity.BOTTOM);

        ObjectAnimator slideUp = ObjectAnimator.ofFloat(mContentView, "translationY", 200, 0);
        slideUp.setDuration(300);
        slideUp.setInterpolator(new DecelerateInterpolator());
        slideUp.start();
    }

    public SlideUpDialog bindView(int layoutId, BindViewListener bindViewListener) {
        if (bindViewListener == null) {
            return this;
        }
        mContentView = LayoutInflater.from(getContext()).inflate(layoutId, null);
        bindViewListener.onBind(mContentView);
        return this;
    }

    public View getContentView() {
        return this.mContentView;
    }

}
