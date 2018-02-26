package aili.com.tests.task_kotlin.task

import aili.com.tests.task_kotlin.bean.TaskData
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * main model 单测用例
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-02-01 15:01
 */
@RunWith(AndroidJUnit4::class)
class MainTaskModelTest : MainTaskContract.onGetTaskListener {
    private lateinit var model: MainTaskModel
    @Before
    fun setUp() {
        model = MainTaskModel()
    }

    @Test
    fun getLocalTaskData() {
        model.getLocalTaskData(this)
    }

    override fun getTaskSuccess(taskList: ArrayList<TaskData>) {
        MatcherAssert.assertThat(taskList.get(0).title, CoreMatchers.`is`("kotiln"))
        MatcherAssert.assertThat(taskList.get(0).description, CoreMatchers.`is`("可java无缝接入"))
    }

    override fun getTaskFailure() {
    }

    override fun emptyData() {
    }


}