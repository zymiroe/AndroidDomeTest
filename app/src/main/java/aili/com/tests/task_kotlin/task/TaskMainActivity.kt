package aili.com.tests.task_kotlin.task

import android.app.Activity
import aili.com.tests.R
import aili.com.tests.task_kotlin.bean.TaskData
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.task_list_main.*


/**
 *  task 首页
 *
 * @author yexiaochai
 * @date 2018-01-30 18:28
 * @version V 1.0
 */
class TaskMainActivity : AppCompatActivity(), View.OnClickListener, MainTaskContract.View, TaskAdapter.onItemClickListener {

    private lateinit var mPresenter: MainTaskPresent
    private lateinit var dataList: ArrayList<TaskData>
    private lateinit var taskAdapter: TaskAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_list_main)

        initData()
    }

    /**
     * init data
     */
    private fun initData() {
        fab_add_task.setOnClickListener(this)
        dataList = ArrayList()
        initRecycler()

        mPresenter = MainTaskPresent(this, this)
        mPresenter.start()
        mPresenter.getTaskList()

    }


    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        content_recycler.setLayoutManager(linearLayoutManager)
        taskAdapter = TaskAdapter(this, dataList!!)
        taskAdapter.setOnItemClickListener(this)
        content_recycler.adapter = taskAdapter
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab_add_task -> {
                startEditActivity("我是默认标题", "我是默认内容")
            }
        }
    }

    /**
     * 跳转到编辑activity
     */
    private fun startEditActivity(title: String, description: String) {
        var intent = Intent(this, AddTaskActivity::class.java).apply {
            putExtra(AddTaskActivity.TITLE_EXTRA, title)
            putExtra(AddTaskActivity.DESCRIPTION_EXTRA, description)
        }
        startActivityForResult(intent, AddTaskActivity.ADD_TASK_ACTIVITY_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == AddTaskActivity.ADD_TASK_ACTIVITY_CODE) {
            val taskData = data?.getParcelableExtra<TaskData>(AddTaskActivity.TASK_DATA)
            if (taskData != null) {
                dataList.add(taskData)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun setPresenter(presenter: MainTaskContract.Presenter) {

    }

    override fun showTaskList(taskList: ArrayList<TaskData>) {
        dataList.clear()
        dataList.addAll(taskList)
        taskAdapter.notifyDataSetChanged()
    }

    override fun onItemClick(dataTask: TaskData) {
        if (dataTask != null) {
            startEditActivity(dataTask.title, dataTask.description)
        }
    }
}