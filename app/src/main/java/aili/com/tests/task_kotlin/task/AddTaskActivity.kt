package aili.com.tests.task_kotlin.task

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import aili.com.tests.R
import com.androiddometest.task.AddTaskFragment
import com.androiddometest.task.AddTaskPresenter
import com.androiddometest.util.addFragment


/**
 * 添加task
 *
 * @author yexiaochai
 * @date 2018-01-23 16:24
 * @version V 1.0
 */
class AddTaskActivity : AppCompatActivity() {
    private lateinit var mTitle: String
    private lateinit var mDescription: String
    private lateinit var mPresenter: AddTaskPresenter

    companion object {
        const val ADD_TASK_ACTIVITY_CODE = 100
        const val TITLE_EXTRA = "title"
        const val DESCRIPTION_EXTRA = "description"
        const val TASK_DATA = "taskData"

    }

    /**这种写法写在伴生类中是报错的, 还在查找原因*/
    object IntentOptions {
        fun createIntent(context: Context, title: String, description: String): Intent {
            return Intent(context, AddTaskActivity::class.java).apply {
                putExtra(title, TITLE_EXTRA).putExtra(description, DESCRIPTION_EXTRA)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_activity)

        initData()
    }

    private fun initData() {
        getIntentExtra()
        addTaskFragment()
    }

    private fun getIntentExtra() {
        mTitle = intent.getStringExtra(TITLE_EXTRA)
        mDescription = intent.getStringExtra(DESCRIPTION_EXTRA)
    }

    private fun addTaskFragment() {
        val addTaskFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as AddTaskFragment?
            ?: AddTaskFragment.newInstance(mTitle, mDescription)
        addFragment(addTaskFragment, R.id.contentFrame)
        mPresenter = AddTaskPresenter(this, addTaskFragment)
    }

}

