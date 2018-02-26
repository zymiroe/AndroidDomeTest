package com.androiddometest.task

import android.app.Activity
import aili.com.tests.R
import aili.com.tests.task_kotlin.bean.TaskData
import aili.com.tests.task_kotlin.task.AddTaskActivity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast


/**
 * 添加 task fragment
 *
 * @author yexiaochai
 * @date 2018-01-23 17:30
 * @version V 1.0
 */
class AddTaskFragment : Fragment(), AddTaskContract.View {

    private lateinit var add_task_title: EditText
    private lateinit var add_task_description: EditText

    private lateinit var mTitle: String
    private lateinit var mDescription: String

    private lateinit var mPresenter: AddTaskContract.Presenter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.add_task_edit, container, false)
        initView(root)
        return root
    }

    /**
     * init view
     * @param root
     */
    private fun initView(root: View) {
        add_task_title = root.findViewById(R.id.add_task_title)
        add_task_description = root.findViewById(R.id.add_task_description)
    }

    /**
     * init date
     */
    private fun initData() {
        initFabButton()
        getBundle()
        add_task_title.setText(mTitle)
        add_task_description.setText(mDescription)
    }


    private fun initFabButton() {
        with(activity.findViewById<FloatingActionButton>(R.id.fab_edit_task_done)) {
            setImageResource(R.drawable.ic_done)
            setOnClickListener {
                mPresenter.saveTaskData(add_task_title.text.toString(), add_task_description.text.toString())
            }
        }
    }

    /**
     * get bundle
     */
    private fun getBundle() {
        var bundle = activity.intent
        if (bundle != null) {
            mTitle = bundle.getStringExtra(TITLE_NAME)
            mDescription = bundle.getStringExtra(DESCRIPTION)
        }
    }

    override
    fun updateTaskData(task: TaskData) {
        if (task.title == mTitle && task.description == mDescription) {
            Toast.makeText(activity, "请重新修改任何一个输入框内容", Toast.LENGTH_LONG).show()
            return
        }
        mPresenter.insertTaskToDB(task)
        val intent = Intent().apply {
            putExtra(AddTaskActivity.TASK_DATA, task)
        }
        activity.setResult(Activity.RESULT_OK, intent)
        activity.finish()
    }

    override
    fun setPresenter(presenter: AddTaskContract.Presenter) {
        Log.d(TAG, "setPresenter————>")
        this.mPresenter = presenter
    }

    /***
     * 伴生对象 类似java static 关键字
     * 因此 kotlin 中静态变量和方法 必须要在伴生对象中
     */
    companion object {
        const val TAG: String = "AddTaskFragment"
        const val TITLE_NAME: String = "title"
        const val DESCRIPTION: String = "description"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(title: String, description: String): AddTaskFragment {
            val fragment = AddTaskFragment()
            val args = Bundle()
            args.putString(AddTaskFragment.TITLE_NAME, title)
            args.putString(AddTaskFragment.DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }
}