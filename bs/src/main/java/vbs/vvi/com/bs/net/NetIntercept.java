package vbs.vvi.com.bs.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Wayne on 2016/7/21.
 */
public class NetIntercept implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();//响应网络前
        //TODO 响应网络前的操作
        request = handleRequest(request);
        Response response = chain.proceed(request);//进行网络响应

        //TODO 网络响应后的操作

        return handleResponse(response);
    }

    private Request handleRequest(Request request) {
        Request.Builder requestClone = request.newBuilder();
        requestClone.url("https://www.baidu.com");
        Log.i("hate", "哈哈 网络响应前啊");
        return requestClone.build();
    }

    private Response handleResponse(Response response) {
        Log.i("hate", "哈哈 网络响应后啊");
        return response;
    }
}
