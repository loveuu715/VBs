package vbs.vvi.com.bs.mvp.view.lce;

import android.support.annotation.UiThread;

import vbs.vvi.com.bs.mvp.view.MvpView;


/**
 * 加载 ,显示,错误的统一管理
 * Created by Wayne on 2016/7/26.
 * Email: loveuu715@163.com
 */
public interface LceView<M> extends MvpView {

    @UiThread
    public void showLoading();

    @UiThread
    public void dismissLoading();

    @UiThread
    public void showContent(M data);

    @UiThread
    public void showError(Throwable e);

}
