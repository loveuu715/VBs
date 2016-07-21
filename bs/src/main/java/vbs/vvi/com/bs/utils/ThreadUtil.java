package vbs.vvi.com.bs.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Wayne on 2016/7/21.
 */
public class ThreadUtil {
    public static ExecutorService sExecutorService = Executors.newCachedThreadPool();
}
