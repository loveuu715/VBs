package vbs.vvi.com.bs.exception;

/**
 * 自定义异常类
 * Created by Wayne on 2016/7/21.
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BaseException() {
        super();
    }
    public BaseException(String exceptionMsg) {
        super(exceptionMsg);
    }
    public BaseException(Throwable throwable) {
        super(throwable);
    }
    public BaseException(String exceptionMsg, Throwable throwable) {
        super(exceptionMsg, throwable);
    }
}
