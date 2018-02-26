package aili.com.tests

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import aili.com.tests.task_java.GitUserDetailsActivity
import aili.com.tests.task_kotlin.task.TaskMainActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author yexiaochai
 * @date 2018-01-30 17:58
 * @version V 1.0
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
    }

    private fun initData() {
        btn_java.setOnClickListener(this)
        btn_kotlin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_java -> {
                var intentJava = Intent(this, GitUserDetailsActivity::class.java)
                startActivity(intentJava)
            }

            R.id.btn_kotlin -> {
                var intentKotlin = Intent(this, TaskMainActivity::class.java)
                startActivity(intentKotlin)
            }
        }

    }
}