package vbs.vvi.com.bs.mvp.presenter;

import android.support.annotation.UiThread;

import vbs.vvi.com.bs.mvp.view.MvpView;


/**
 * Created by Wayne on 2016/7/26.
 * Email: loveuu715@163.com
 */
public interface BaseMvpPresenter<V extends MvpView> {
    /**
     * 关联视图
     * @param view
     */
    @UiThread
    void attachView(V view);

    /**
     * 当视图被销毁时调用
     * @param view
     */
    @UiThread
    void detachView(V view);

}
