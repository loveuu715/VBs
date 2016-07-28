package vbs.vvi.com.bs.common.exception;

import java.text.DateFormat;

/**
 * 异常处理助手
 * Created by Wayne on 2016/7/21.
 */
public abstract class ExceptionHelper implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashHelper";

    protected static final DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //如果正常处理,则认为捕获了异常,否则没有捕获到异常
        if (handleException(throwable)){
            try {
                //保存异常信息,确保异常信息能够正常保存
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //程序退出
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    //自定义异常处理,自己写异常处理的逻辑
    public abstract boolean handleException(Throwable ex);
}
