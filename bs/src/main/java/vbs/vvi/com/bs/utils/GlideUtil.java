package vbs.vvi.com.bs.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Wayne on 2016/7/22.
 * Email: loveuu715@163.com
 */
public class GlideUtil {

    public static void display(Context context, ImageView iv, String path) {
        Glide.with(context).load(path).into(iv);
    }

    public static void displayWithBlur(Context context, ImageView iv, String path, int blur) {
        Glide.with(context).load(path).bitmapTransform(new BlurTransformation(context, blur)).into(iv);
    }

}
