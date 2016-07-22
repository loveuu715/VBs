package vbs.vvi.com.bs.base;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Wayne on 2016/7/22.
 * Email: loveuu715@163.com
 */
public abstract class BasePresenter<T extends BaseView> {

    private T mView;
    private Context mContext;
    private Intent mIntent;


    public BasePresenter(T view) {
        this.mView = view;
        init();
    }

    protected abstract void init();

    public BasePresenter(T view, Intent intent) {
        this.mView = view;
        this.mIntent = intent;
    }

    public T getView() {
        return mView;
    }

    public Context getContext() {
        return mContext;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void onDestory() {
    }

    public void onResume() {
    }

    public void onPause() {
    }
}
