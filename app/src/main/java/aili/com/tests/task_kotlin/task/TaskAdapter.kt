package aili.com.tests.task_kotlin.task

import aili.com.tests.R
import aili.com.tests.task_kotlin.bean.TaskData
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.task_item.view.*


/**
 *  task adapter
 *
 * @author yexiaochai
 * @date 2018-01-24 16:15
 * @version V 1.0
 */
class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private var mContext: Context
    private var mDataTaskList: List<TaskData>

    private lateinit var mListener: onItemClickListener

    constructor(context: Context, dataTask: List<TaskData>) {
        this.mContext = context
        this.mDataTaskList = dataTask
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(mContext).inflate(R.layout.task_item, null))
    }

    override fun getItemCount() = mDataTaskList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindHolder(mDataTaskList[position])
        holder.itemView.setOnClickListener {
            if (mListener != null) {
                mListener.onItemClick(mDataTaskList[position])
            }
        }

    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindHolder(taskData: TaskData) = with(itemView) {
            itemView.tv_title.text = taskData.title
            itemView.tv_content.text = taskData.description
        }

    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        this.mListener = listener
    }

    interface onItemClickListener {
        fun onItemClick(dataTask: TaskData)
    }

}