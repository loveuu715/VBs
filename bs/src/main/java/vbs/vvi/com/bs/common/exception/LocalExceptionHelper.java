package vbs.vvi.com.bs.common.exception;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import vbs.vvi.com.bs.utils.FileUtil;
import vbs.vvi.com.bs.utils.ThreadUtil;

/**
 * 异常信息处理及保存
 * Created by Wayne on 2016/7/21.
 */
public class LocalExceptionHelper extends ExceptionHelper {

    private Context mContext;

    public LocalExceptionHelper(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean handleException(Throwable ex) {
        if (ex == null)
            return false;

        ThreadUtil.execut(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉, 应用出现异常，正在从异常中恢复...", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        });
        saveLog(ex);
        return true;
    }

    /**
     * 保存异常日志信息
     *
     * @param ex
     */
    private void saveLog(Throwable ex) {
        OutputStream out = null;
        try {
            File path = new File(FileUtil.getDiskCacheDir(mContext) + "/log");
            if (!path.exists()) {
                path.mkdirs();
            }

            File errorFile = new File(path + "/crash.txt");

            if (!errorFile.exists()) {
                errorFile.createNewFile();
            }

            out = new FileOutputStream(errorFile, true);
            out.write(("\n\n-----错误分割线" + dateFormat.format(new Date()) + "-----\n\n").getBytes());
            PrintStream stream = new PrintStream(out);
            ex.printStackTrace(stream);
            stream.flush();
            out.flush();
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
}
