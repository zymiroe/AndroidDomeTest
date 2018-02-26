package aili.com.tests.task_kotlin.db

import aili.com.tests.task_kotlin.bean.TaskData


/**
 * 数据库 数据 增删改查
 *
 * @author yexiaochai
 * @date 2018-01-24 22:48
 * @version V 1.0
 */

interface TaskStorage {
    /**
     * 保存 task
     * @param taskData
     */
    fun saveTask(taskData: TaskData)

    /**
     * 删除 task
     * @param taskData
     */
    fun deleteTask(taskData: TaskData)

    /**
     * 查询 task
     * @param taskId
     */
    fun queryTask(taskId: String): TaskData

    /**
     * 根据id 查询 task
     * @param taskId
     */
    fun updateTask(taskId: String)

    /**
     * 查询所有taskList
     */
    fun queryTaskList(): ArrayList<TaskData>

}
