package aili.com.tests;

import android.app.Application;
import android.content.Context;


/**
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-01-31 10:42
 */

public class TestApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     * 获取app context
     *
     * @return
     */
    public static Context getAppContext() {
        return mContext;
    }
}
