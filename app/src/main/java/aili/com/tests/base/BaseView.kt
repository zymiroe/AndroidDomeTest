package com.androiddometest




/**
 * 父 view
 *
 * @author yexiaochai
 * @date 2018-01-29 09:41
 * @version V 1.0
 */
interface BaseView<T> {
    /**presenter实例传入view中,其调用时机是presenter实现类的构造函数中 */
    fun setPresenter(presenter: T)
}