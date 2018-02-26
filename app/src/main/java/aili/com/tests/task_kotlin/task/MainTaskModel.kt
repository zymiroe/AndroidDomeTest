package aili.com.tests.task_kotlin.task

import aili.com.tests.task_kotlin.bean.TaskData
import aili.com.tests.task_kotlin.db.TaskDBStorage


/**
 * main task model
 *
 * @author yexiaochai
 * @date 2018-01-30 19:03
 * @version V 1.0
 */
 class MainTaskModel {

    /**
     * 从本地获取 task 数据
     *
     * @param listener
     */
    fun getLocalTaskData(listener: MainTaskContract.onGetTaskListener) {
        val taskStorage = TaskDBStorage()
        if (taskStorage != null && listener != null) {
            val list: ArrayList<TaskData> = taskStorage.queryTaskList()
            if (list != null && list.size > 0) {
                listener.getTaskSuccess(list)
            } else {
                listener.emptyData()
            }
        }
    }

}