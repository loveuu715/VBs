package vbs.vvi.com.bs.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import vbs.vvi.com.bs.BaseApplication;

/**
 * Created by Wayne on 2016/7/25.
 * Email: loveuu715@163.com
 */
public class PictureUtil {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurBitmap(Bitmap bitmap) {
        return getBlurBitmap(bitmap);
//        return getBlurBitmapWithFastBlur(bitmap, 35, true);
    }

    private  static Bitmap getBlurBitmapWithFastBlur(Bitmap bitmap, int blur, boolean canReuseInBitmap){
        return FastBlur.doBlur(bitmap, blur, canReuseInBitmap);
    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Bitmap getBlurBitmap(Bitmap bitmap) {
        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(BaseApplication.getApplication());

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(25.0f);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;
    }
}
