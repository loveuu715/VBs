package vbs.vvi.com.bs;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wayne on 2016/7/19.
 */
public class BaseApplication extends Application {

    private static Handler sMainHandler;
    private static Looper sMainLooper;
    private static Thread sMainThread;
    private static int sMainThreadId;
    private static BaseApplication sInstance;
    private static List<Activity> sActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        BaseApplication.sInstance = this;
        BaseApplication.sMainHandler = new Handler();
        BaseApplication.sMainLooper = getMainLooper();
        BaseApplication.sMainThread = Thread.currentThread();
        BaseApplication.sMainThreadId = android.os.Process.myTid();
        BaseApplication.sActivities = new LinkedList<Activity>();
    }

    public static Handler getsMainHandler() {
        return sMainHandler;
    }

    public static Looper getsMainLooper() {
        return sMainLooper;
    }

    public static Thread getsMainThread() {
        return sMainThread;
    }

    public static int getsMainThreadId() {
        return sMainThreadId;
    }

    public static BaseApplication getApplication() {
        return sInstance;
    }

    public static void activityEnqueue(Activity activity) {
        if (activity != null && !sActivities.contains(activity))
            sActivities.add(activity);
    }

    public static void finishAllActivities() {
        for (Activity activity : sActivities) {
            if (!activity.isFinishing())
                activity.finish();
        }
    }

    public static void finishActivityBut(Activity activity) {
        if (activity != null) {
            for (Activity act : sActivities) {
                if (act != activity && !act.isFinishing())
                    act.finish();
            }
        }
        sActivities.clear();
        sActivities.add(activity);
    }
}
