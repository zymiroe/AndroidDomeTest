package aili.com.tests.task_java.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @Description okHttp 请求服务器
 * @Author yexiaochai
 * @Date 2018-02-03 17:16
 * @Version V 1.0
 */
public class OkHttpUtils {

    private static OkHttpClient okHttpClinet;
    private static final int TIME_OUT = 30;//超时 秒作为单位 SECONDS

    private static final String TAG = "OkHttpUtils";

    public static OkHttpClient getInstance() {
        if (okHttpClinet == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpClinet == null) {
                    okHttpClinet = new OkHttpClient();
                    okHttpClinet.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);
                    okHttpClinet.newBuilder().readTimeout(TIME_OUT, TimeUnit.SECONDS);
                }
            }
        }
        return okHttpClinet;
    }
}
