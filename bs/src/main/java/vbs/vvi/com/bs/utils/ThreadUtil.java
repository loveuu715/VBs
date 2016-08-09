package vbs.vvi.com.bs.utils;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Wayne on 2016/7/21.
 */
public class ThreadUtil {
    private static Handler sHandler = new Handler();
    private static ExecutorService sExecutorService = Executors.newCachedThreadPool();

    public static void execut(Runnable runnable) {
        sExecutorService.execute(runnable);
    }

    public static void safeRun(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void safeRunDelay(Runnable runnable, int time) {
        sHandler.postDelayed(runnable, time);
    }
}
