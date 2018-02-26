package aili.com.tests.task_kotlin.task

import aili.com.tests.task_kotlin.bean.TaskData
import android.content.Context


/**
 * main presenter
 *
 * @author yexiaochai
 * @date 2018-01-30 19:02
 * @version V 1.0
 */
class MainTaskPresent : MainTaskContract.Presenter, MainTaskContract.onGetTaskListener {

    private var mContext: Context
    private var mView: MainTaskContract.View
    private lateinit var mTaskModel: MainTaskModel

    constructor(context: Context, view: MainTaskContract.View) {
        this.mContext = context
        this.mView = view
    }

    override fun start() {
        mTaskModel = MainTaskModel()
    }

    override fun getTaskList() {
        mTaskModel.getLocalTaskData(this)
    }

    override fun getTaskSuccess(taskList: ArrayList<TaskData>) {
        mView.showTaskList(taskList)
    }

    override fun getTaskFailure() {

    }

    override fun emptyData() {

    }


}