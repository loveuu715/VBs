package vbs.vvi.com.bs.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import vbs.vvi.com.bs.R;

/**
 * Created by Wayne on 2016/7/19.
 */
public abstract class BaseView<T extends View> {
    private T mView;
    protected Context mContext;
    protected int mLayoutId;
    private BasePresenter mBasePresenter;
    private View stateView;
    private View tempView;
    private String mEmptyText;


    public BaseView(T view) {
        mView = view;
        this.mContext = mView.getContext();
        init();
    }

    public BaseView(Context context, int layoutId) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mView = getView();
        init();
    }

    /**
     * 初始化的方法
     */
    protected abstract void init();

    public void setText(int id, String value) {
        if (getTextView(id) != null) {
            getTextView(id).setText(value);
        }
    }

    public void setText(int id, int strResId) {
        if (getTextView(id) != null) {
            getTextView(id).setText(strResId);
        }
    }

    public void setTextSize(int id, float size) {
        getTextView(id).setTextSize(size);
    }

    public float getTextSize(int id) {
        return getTextView(id).getTextSize();
    }

    public void setTxtColor(int id, int color) {
        getTextView(id).setTextColor(color);
    }

    public TextView getTextView(int id) {
        return (TextView) getView().findViewById(id);
    }

    public EditText getEditText(int id) {
        return (EditText) getView().findViewById(id);
    }

    public ImageView getImageView(int id) {
        return (ImageView) getView().findViewById(id);
    }

    public LinearLayout getLinearLayout(int id) {
        return (LinearLayout) getView().findViewById(id);
    }

    public RelativeLayout getRelativeLayout(int id) {
        return (RelativeLayout) getView().findViewById(id);
    }

    public void setVisibility(int visibility, int id) {
        View view = getView(id);
        view.setVisibility(visibility);
    }

    public Intent getIntent() {
        return ((Activity) mContext).getIntent();
    }

    public void setVisibility(int visibility, Integer... ids) {
        if (ids != null) {
            for (Integer id : ids) {
                getView(id).setVisibility(visibility);
            }
        }
    }

    public void setSelected(int id, boolean selected) {
        getView(id).setSelected(selected);
    }

    public void setBackground(int id, int resId) {
        getView(id).setBackgroundResource(resId);
    }

    public void setEnable(int id, boolean enabled) {
        getView(id).setEnabled(enabled);
    }

    /**
     * 获得presenter
     *
     * @return
     */
    public BasePresenter getPresenter() {
        return mBasePresenter;
    }

    /**
     * 绑定presenter
     *
     * @param presenter
     */
    public void setPresenter(BasePresenter presenter) {
        this.mBasePresenter = presenter;
    }

    //视图管理
    public enum STATE {
        LOADING, NO_NETWORK, ERROR, EMPTY, SUCCESS
    }

    //当前视图状态 默认加载成功
    private STATE mCurrentState = STATE.SUCCESS;
    private int mErrorLayoutId = -1;
    private int mEmptyLayoutId = -1;
    private int mNoNetworkLayoutId = -1;
    private int mLoadingLayoutId = -1;

    private static final int STATE_TAG = 0X20160720;
    private static final int KEEP_HIDE_TAG = 0X20160722;

    public View getStateView() {
        return stateView;
    }
    public STATE getViewState() {
        return mCurrentState;
    }

    public void setErrorLayoutId(int errorLayoutId) {
        mErrorLayoutId = errorLayoutId;
    }

    public void setEmptyLayoutId(int emptyLayoutId) {
        mEmptyLayoutId = emptyLayoutId;
    }

    public void setNoNetworkLayoutId(int noNetworkLayoutId) {
        mNoNetworkLayoutId = noNetworkLayoutId;
    }

    public void setLoadingLayoutId(int loadingLayoutId) {
        mLoadingLayoutId = loadingLayoutId;
    }

    public void setViewState(STATE state, int... notHideResIds) {
        this.mCurrentState = state;
        switch (mCurrentState) {
            case LOADING://正在加载视图
                if (mLoadingLayoutId != -1) {
                    setViewStateWithLayout(STATE.LOADING, mLoadingLayoutId, notHideResIds);
                } else {//默认
                    setViewStateWithLayout(STATE.LOADING, mLoadingLayoutId, notHideResIds);
                }
                break;
            case ERROR://错误视图
                if (mErrorLayoutId != -1) {
                    setViewStateWithLayout(STATE.ERROR, mErrorLayoutId, notHideResIds);
                } else {//默认
                    setViewStateWithLayout(STATE.ERROR, mErrorLayoutId, notHideResIds);
                }
                break;
            case NO_NETWORK://无网络视图
                if (mNoNetworkLayoutId != -1) {
                    setViewStateWithLayout(STATE.NO_NETWORK, mNoNetworkLayoutId, notHideResIds);
                } else {//默认
                    setViewStateWithLayout(STATE.NO_NETWORK, mNoNetworkLayoutId, notHideResIds);
                }
                break;
            case EMPTY://空数据视图
                if (mEmptyLayoutId != -1) {
                    setViewStateWithLayout(STATE.EMPTY, mEmptyLayoutId, notHideResIds);
                } else {//默认
                    setViewStateWithLayout(STATE.EMPTY, mEmptyLayoutId, notHideResIds);
                }
                break;
            case SUCCESS://成功视图
                setSuccessView();
                break;
        }
    }

    private void setSuccessView() {
        if (mView != null && mView instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) mView;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                Object tag = child.getTag();
                if (tag != null) {
                    if ((Integer) tag != STATE_TAG && (Integer) tag != KEEP_HIDE_TAG) {
                        child.setVisibility(View.VISIBLE);
                    } else {
                        if ((Integer)tag != KEEP_HIDE_TAG) {
                            stateView = child;
                        } else {
                            child.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    child.setVisibility(View.VISIBLE);
                }
            }
            if (stateView != null) {
                vg.removeView(stateView);
                stateView = null;
            }
            if (vg instanceof ScrollView) {
                if (tempView != null) {
                    tempView.setVisibility(View.VISIBLE);
                    vg.addView(tempView);
                }
            }
        }
    }

    public void setViewStateWithLayout(STATE state, int layoutId, int... notHideResIds) {
        this.mCurrentState = state;
        switch (mCurrentState) {
            case LOADING:
            case NO_NETWORK:
            case ERROR:
            case EMPTY:
                hideLayout(layoutId, notHideResIds);
                break;
            default:
                break;
        }
    }

    private void hideLayout(int layoutId, int... notHideResIds) {
        if (mView != null && mView instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) mView;
            int vgCount = vg.getChildCount();
            if (vgCount == 0) {
                addStateView(vg, layoutId);
            } else if (vgCount == 1) {
                View child = vg.getChildAt(0);
                Integer tag = (Integer) child.getTag();
                if (tag != null) {
                    if (tag == STATE_TAG) {
                        vg.removeView(child);
                        addStateView(vg, layoutId);
                    } else {
                        if (child.getVisibility() == View.GONE) {
                            child.setTag(KEEP_HIDE_TAG);
                        }
                        child.setVisibility(View.GONE);
                        //处理不隐藏的组件
                        if (notHideResIds != null && notHideResIds.length > 0) {
                            for (Integer notHideId : notHideResIds) {
                                if (child.getId() == notHideId) {
                                    child.setVisibility(View.VISIBLE);
                                    break;
                                }
                            }
                        }
                        addStateView(vg, layoutId);
                    }
                } else {
                    if (child.getVisibility() == View.GONE) {
                        child.setTag(KEEP_HIDE_TAG);
                    }
                    child.setVisibility(View.GONE);
                    addStateView(vg, layoutId);

                }
            } else {
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    if (child.getVisibility() == View.GONE) {
                        child.setTag(KEEP_HIDE_TAG);
                    }
                    child.setVisibility(View.GONE);
                    // 显示不隐藏的视图
                    if (notHideResIds != null && notHideResIds.length > 0) {
                        for (Integer notHideId : notHideResIds) {
                            if (child.getId() == notHideId) {
                                child.setVisibility(View.VISIBLE);
                                break;
                            }
                        }
                    }
                }
                addStateView(vg, layoutId);
            }
        }
    }

    private void addStateView(ViewGroup vg, int layoutId) {
        // add StateView之前先删除原有的StateView
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            Integer tag = (Integer) child.getTag();
            if (tag != null && tag == STATE_TAG) {
                vg.removeView(child);
            }
        }

        // 创建StateView
        stateView = View.inflate(mView.getContext(), layoutId, null);
        //error click
        if (layoutId == R.layout.state_error) {//错误视图
           /* View buttonError = stateView.findViewById(R.id.buttonError);
            if (buttonError != null) {
                buttonError.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onErrorClickListener != null) {
                            onErrorClickListener.onErrorClickListener(v);
                        }
                    }
                });
            }*/
        }else if(layoutId == R.layout.state_empty){//空数据视图
            //empty text
           /* TextView textViewMessage = (TextView) stateView.findViewById(R.id.textViewMessage);
            if(!TextUtils.isEmpty(mEmptyText)){
                textViewMessage.setText(mEmptyText);
            }*/
        }

        stateView.setTag(STATE_TAG);
        // 对于ScrollView需要特殊处理，因为它只能有一个Child
        if (vg instanceof ScrollView) {
            if (vg.getChildCount() > 0) {
                tempView = vg.getChildAt(0);
                vg.removeAllViews();
            }
        }
        // 添加StateView
        if (vg instanceof RelativeLayout || vg instanceof FrameLayout) {
            vg.addView(stateView, 0);
        } else {
            if (vg instanceof LinearLayout) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT);
                lp.gravity = Gravity.CENTER;
                stateView.setLayoutParams(lp);
            }
            vg.addView(stateView);
        }
    }

    public void bind(View.OnClickListener listener, Integer... viewResIds) {
        if (viewResIds != null && viewResIds.length > 0) {
            for (Integer id : viewResIds) {
                getView(id).setOnClickListener(listener);
            }
        }
    }

    public View getView(int id) {
        return getView().findViewById(id);
    }


    /**
     * 获得绑定的view
     *
     * @return
     */
    public T getView() {
        if (mView != null)
            return mView;
        mView = (T) LayoutInflater.from(mContext).inflate(mLayoutId, null);
        return mView;
    }

    /**
     * 获得Context
     *
     * @return
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * 只返回Activity 否则返回null
     *
     * @return
     */
    public Activity getActivity() {
        if (mContext != null)
            if (mContext instanceof Activity)
                return (Activity) mContext;
        return null;
    }


    OnErrorClickListener onErrorClickListener;

    public void setOnErrorClickListener(OnErrorClickListener onErrorClickListener) {
        this.onErrorClickListener = onErrorClickListener;
    }

    public OnErrorClickListener getOnErrorClickListener(){
        return onErrorClickListener;
    }

    public interface OnErrorClickListener {
        void onErrorClickListener(View v);
    }

    public void setEmptyText(String emptyText){
        mEmptyText = emptyText;
    }

}
