package aili.com.tests.task_java.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @FileName RetrofitUtils
 * @Description
 * @Author yexiaochai
 * @Date 2015-09-08 17:16
 * @Version V 1.0
 */
public class RetrofitUtils {
    public static RetrofitUtils instance;
    private static Retrofit singleton;


    public static RetrofitUtils getInstance() {
        if (instance == null) {
            instance = new RetrofitUtils();
        }
        return instance;
    }

    public <T> T createApi(Class<T> clazz) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.client(OkHttpUtils.getInstance());
                    builder.addConverterFactory(GsonConverterFactory.create());
                    builder.baseUrl("https://api.github.com");

                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }

}
