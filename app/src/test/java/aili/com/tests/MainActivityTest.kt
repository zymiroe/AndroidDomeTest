package aili.com.tests

import android.content.Intent
import android.os.Build
import android.widget.Button
import aili.com.tests.base.BaseTestApplication
import aili.com.tests.base.BaseTestTestRunner
import aili.com.tests.task_kotlin.task.TaskMainActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config
import org.robolectric.annotation.internal.DoNotInstrument
import org.robolectric.shadows.ShadowApplication


/**
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-03 11:24
 */
@RunWith(BaseTestTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP), application = BaseTestApplication::class)
// 因为我第一次创建包的名字前面加上了 android 字样和库的自检产生冲突（private final $$robot$$setUser()） 所有用这个注解忽略掉
// 后面修改了包名  为了不忘记，没有删除
@DoNotInstrument
class MainActivityTest {
    @Test
    fun clickButtonStartTaskMainActivity() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        activity.findViewById<Button>(R.id.btn_kotlin).performClick()

        val expectedIntent = Intent(activity, TaskMainActivity::class.java)
        val actual = ShadowApplication.getInstance().nextStartedActivity
        Assert.assertEquals(expectedIntent.getComponent(), actual.component)
    }
}