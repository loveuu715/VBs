package vbs.vvi.com.bs.common.constants;

import vbs.vvi.com.bs.BaseApplication;
import vbs.vvi.com.bs.utils.FileUtil;

/**
 * 记录应用中的常量
 * Created by Wayne on 2016/7/21.
 */
public interface Constants {
    String BASE_URL = "https://www.baidu.com";
    String APP_CACHE_DIR = FileUtil.getDiskCacheDir(BaseApplication.getApplication()) + "/BsCache";
}
