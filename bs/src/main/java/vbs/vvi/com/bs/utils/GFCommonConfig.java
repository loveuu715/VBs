package vbs.vvi.com.bs.utils;

import cn.finalteam.galleryfinal.FunctionConfig;

/**
 * Created by Wayne on 2016/8/10.
 * Email: loveuu715@163.com
 */
public class GFCommonConfig {

    public static FunctionConfig single() {
        return new FunctionConfig.Builder()
                .setEnableCamera(false)//显示相机
                .setEnableRotate(false)//支持旋转
                .setEnableEdit(false)//不支持裁剪
                .setForceCropEdit(false)//裁剪成功后是否编辑
                .build();
    }

    public static FunctionConfig crop() {
        return new FunctionConfig.Builder()
                .setEnableCamera(false)//显示相机
                .setEnableRotate(true)//支持旋转
                .setForceCrop(true)//强制裁剪
                .setCropSquare(true)//正方形裁剪
                .setForceCropEdit(false)//裁剪成功后是否编辑
                .setCloseSelectAfter(true)//裁剪时是否关闭选择界面
                .build();
    }


}
