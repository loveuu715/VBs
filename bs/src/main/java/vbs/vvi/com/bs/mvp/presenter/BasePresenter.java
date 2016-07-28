package vbs.vvi.com.bs.mvp.presenter;


import vbs.vvi.com.bs.common.exception.BaseException;
import vbs.vvi.com.bs.mvp.view.MvpView;

/**
 * presenter基类
 * Created by Wayne on 2016/7/26.
 * Email: loveuu715@163.com
 */
public class BasePresenter<V extends MvpView> implements BaseMvpPresenter<V> {

    private V mView;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView(V view) {
        this.mView = null;
    }

    public boolean isViewAttach() {
        return this.mView == null ? false : true;
    }

    public V getView() {
        return this.mView;
    }

    public void checkViewAttached() {
        if (!isViewAttach()) throw new ViewNotAttachedException();
    }

    public static class ViewNotAttachedException extends BaseException {
        public ViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
