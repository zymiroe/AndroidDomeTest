package aili.com.tests.robolectric.db

import android.os.Build
import aili.com.tests.base.BaseTestApplication
import aili.com.tests.base.BaseTestTestRunner
import aili.com.tests.task_kotlin.bean.TaskData
import aili.com.tests.task_kotlin.db.TaskDBStorage
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * 数据库增删改查 单测用例
 * @author yexiaochai
 * @version V 1.0
 * @date 2018-01-31 15:29
 */
@RunWith(BaseTestTestRunner::class)
@Config(sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP), application = BaseTestApplication::class)
class TaskDBStorageTest {

    private lateinit var mTaskDBStorage: TaskDBStorage

    @Before
    fun setUp() {
        mTaskDBStorage = TaskDBStorage()
        mTaskDBStorage.openDB()
    }

    @After
    fun closeDb() {
        mTaskDBStorage.closeDB()
    }

    @Test
    fun testPreConditions() {
        Assert.assertNotNull(mTaskDBStorage)
    }

    @Test
    fun saveTask() {
        val taskData = TaskData("单测title", "单测内容", "1s2_232de")
        mTaskDBStorage.saveTask(taskData)
        val taskList: ArrayList<TaskData> = mTaskDBStorage.queryTaskList()
        MatcherAssert.assertThat<Int>(taskList.size, CoreMatchers.`is`(1))
        MatcherAssert.assertThat(taskList.get(0).title, CoreMatchers.`is`("单测title"))
        MatcherAssert.assertThat(taskList.get(0).description, CoreMatchers.`is`("单测内容"))
        mTaskDBStorage.deleteTask(taskData)
    }

    @Test
    fun deleteTask() {
        val taskData = TaskData("单测title", "单测内容", "1s2_232de")
        mTaskDBStorage.deleteTask(taskData)

    }

    @Test
    fun queryTask() {
        val taskDataQuery = TaskData("单测查询title", "单测查询内容", "1s2_232_query")
        mTaskDBStorage.saveTask(taskDataQuery)
        val taskData = mTaskDBStorage.queryTask("1s2_232_query")
        MatcherAssert.assertThat(taskData.title, CoreMatchers.`is`("单测查询title1"))
        MatcherAssert.assertThat(taskData.description, CoreMatchers.`is`("单测查询内容"))
        mTaskDBStorage.deleteTask(taskDataQuery)
    }

    @Test
    fun queryTaskList() {
        val taskData = TaskData("单测title", "单测内容", "1s2_232de")
        mTaskDBStorage.saveTask(taskData)
        val taskList: ArrayList<TaskData> = mTaskDBStorage.queryTaskList()
        // The list is empty
        MatcherAssert.assertThat(taskList.size, CoreMatchers.`is`(1))
    }


    private fun assertTask(task: TaskData?, id: String, title: String, description: String) {
        MatcherAssert.assertThat<TaskData>(task as TaskData, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(task.id, CoreMatchers.`is`(id))
        MatcherAssert.assertThat(task.title, CoreMatchers.`is`(title))
        MatcherAssert.assertThat(task.description, CoreMatchers.`is`(description))
    }
}