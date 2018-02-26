package aili.com.tests.task_kotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * 数据库操作
 *
 * @author yexiaochai
 * @date 2018-01-29 21:58
 * @version V 1.0
 */
class DBOpenHelper : SQLiteOpenHelper {

    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(db: SQLiteDatabase) {
        createTab(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS)
        onCreate(db)
    }

    /**
     * crate tab
     */
    private fun createTab(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
            TABLE_PRODUCTS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TASK_ID + " TEXT,"
            + COLUMN_TITLE + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT" + ")")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "task.db"
        const val TABLE_PRODUCTS = "task"

        const val COLUMN_ID = "_id"
        const val COLUMN_TASK_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
    }


}