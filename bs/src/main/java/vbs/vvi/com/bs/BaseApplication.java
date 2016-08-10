package vbs.vvi.com.bs;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import okhttp3.OkHttpClient;
import vbs.vvi.com.bs.net.NetIntercept;
import vbs.vvi.com.bs.utils.GFImageLoader;
import vbs.vvi.com.bs.utils.LogUtil;
import vbs.vvi.com.bs.utils.TipUtil;

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
        MultiDex.install(this);
        super.onCreate();
        init();
    }

    private void init() {
        initConstants();
        initGalleryFinalConfig();
//        initOkHttpConfig();
        initLogConfig();
        initToast();
    }

    private void initGalleryFinalConfig() {
        //ThemeConfig.CYAN
        int color = Color.rgb(0x33, 0x99, 0xee);
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(color)
                .setFabNornalColor(color)
                .setCheckSelectedColor(color)
                .setFabPressedColor(color)
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)//相机是否可见
                .setEnableEdit(false)//是否可编辑
                .setEnableCrop(true)//是否可裁剪
                .setEnableRotate(true)//是否可旋转
                .setCropSquare(true)//是否正方形裁剪
                .setEnablePreview(true)//是否显示预览
                .setMutiSelectMaxSize(12)//最大选择数量
                .build();

        //配置imageloader
        ImageLoader imageloader = new GFImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageloader, theme)
//                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)
                .setAnimation(R.anim.scale_in_90)
                .build();
        GalleryFinal.init(coreConfig);
    }

    private void initToast() {
        TipUtil.isShow = true;
    }

    private void initLogConfig() {
        LogUtil.isDebug = true;
    }

    private void initConstants() {
        BaseApplication.sInstance = this;
        BaseApplication.sMainHandler = new Handler();
        BaseApplication.sMainLooper = getMainLooper();
        BaseApplication.sMainThread = Thread.currentThread();
        BaseApplication.sMainThreadId = android.os.Process.myTid();
        BaseApplication.sActivities = new LinkedList<Activity>();

    }

    private void initOkHttpConfig() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("HATE"))
                .addInterceptor(new NetIntercept())
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
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

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
        super.attachBaseContext(base);
    }
}
