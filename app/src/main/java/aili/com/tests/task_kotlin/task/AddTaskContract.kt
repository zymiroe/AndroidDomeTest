package com.androiddometest.task

import aili.com.tests.task_kotlin.bean.TaskData
import com.androiddometest.BasePresenter
import com.androiddometest.BaseView


/**
 * 添加 task 契约类
 *
 * @author yexiaochai
 * @date 2018-01-23 17:16
 * @version V 1.0
 */
class AddTaskContract {

    interface Presenter : BasePresenter {
        fun saveTaskData(title: String, description: String)

        fun insertTaskToDB(task: TaskData)

    }

    interface View : BaseView<Presenter> {
        fun updateTaskData(task: TaskData)
    }

}