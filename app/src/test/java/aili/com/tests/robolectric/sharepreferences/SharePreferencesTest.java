package aili.com.tests.robolectric.sharepreferences;

import android.os.Build;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import aili.com.tests.BuildConfig;
import aili.com.tests.base.BaseTestTestRunner;
import aili.com.tests.task_java.Preferences;


/**
 * sp 单测用例
 *
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-01-31 16:29
 */
@RunWith(BaseTestTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
// 因为我包的名字前面加上了 android 字样和库的自检产生冲突（private final $$robot$$setUser()） 所有用这个注解忽略掉
//@DoNotInstrument
public class SharePreferencesTest {
    private static final String TAG = "SharePreferencesTest";

    private Preferences preferences;

    @Before
    public void setUp() throws Exception {
        //输出日志
        ShadowLog.stream = System.out;
        preferences = new Preferences(RuntimeEnvironment.application);
    }

    @Test
    public void setUserTest() throws Exception {
        preferences.setUserName("yexiaochai");
        Log.i(TAG, preferences.getUserName());
        Assert.assertEquals("yexiaochai", preferences.getUserName());
    }

    @Test
    public void getDefaultUserTest() throws Exception {
        Assert.assertEquals("", preferences.getUserName());
        Log.i(TAG, preferences.getUserName());
    }


}
