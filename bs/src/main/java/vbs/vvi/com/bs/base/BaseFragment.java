package vbs.vvi.com.bs.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vbs.vvi.com.bs.common.events.EventObject;

/**
 * Created by Wayne on 2016/7/22.
 * Email: loveuu715@163.com
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;
    protected Context mContext;
    private Unbinder mUnbinder;
    protected IFragmentListener mIFragmentListener;
    private InputMethodManager methodManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        methodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), null);
            mUnbinder = ButterKnife.bind(this, mView);
            init();
        }
        return mView;
    }

    protected abstract int getLayoutId();

    protected abstract void init();


    public void setIFragmentListener(IFragmentListener iFragmentListener) {
        this.mIFragmentListener = iFragmentListener;
    }

    public void onEvent(EventObject eo) {
    }


    /**
     * @param time
     * @Description: 显示键盘
     */
    protected void showInput(final View v, int time) {
        //		methodManager.showSoftInputFromInputMethod(v.getWindowToken(), 0);
        //自动弹出键盘
        if (time == 0) {
            time = 300;
        }
        new Timer().schedule(new TimerTask() {
            public void run() {
                methodManager.showSoftInput(v, 0);
            }
        }, time);
    }

    /**
     * @Description: 隐藏键盘
     */
    protected void hideInputNotUse() {
        methodManager.hideSoftInputFromWindow(getActivity().getWindow().peekDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void hideInput(View v) {
        methodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mUnbinder.unbind();
    }


}
