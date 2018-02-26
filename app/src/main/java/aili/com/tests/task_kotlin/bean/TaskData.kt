package aili.com.tests.task_kotlin.bean

import android.os.Parcel
import android.os.Parcelable
import java.util.*


/**
 *  Task 实体类
 *
 * @author yexiaochai
 * @date 2018-01-29 17:08
 * @version V 1.0
 */
data class TaskData constructor(var title: String, var description: String, var id: String = UUID.randomUUID().toString()) : Parcelable {
    constructor() : this("", "", "")

    val isEmpty get() = title.isEmpty() && description.isEmpty()

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskData> {
        override fun createFromParcel(parcel: Parcel): TaskData {
            return TaskData(parcel)
        }

        override fun newArray(size: Int): Array<TaskData?> {
            return arrayOfNulls(size)
        }
    }
}