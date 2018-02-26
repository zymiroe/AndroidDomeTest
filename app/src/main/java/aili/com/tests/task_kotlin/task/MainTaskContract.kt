package aili.com.tests.task_kotlin.task

import aili.com.tests.task_kotlin.bean.TaskData
import com.androiddometest.BasePresenter
import com.androiddometest.BaseView


/**
 * main 契约类
 *
 * @author yexiaochai
 * @date 2018-01-30 18:57
 * @version V 1.0
 */
class MainTaskContract {

    interface Presenter : BasePresenter {
        fun getTaskList()
    }

    interface View : BaseView<Presenter> {
        fun showTaskList(taskList: ArrayList<TaskData>)
    }

    interface onGetTaskListener {
        fun getTaskSuccess(taskList: ArrayList<TaskData>)
        fun getTaskFailure()
        fun emptyData()
    }
}