package ru.dvc.kotlin_chat_clean.domain.type

import timber.log.Timber


/**
 *
 *
 * @author EY.Samarin
 */
open class HandleOnce<out T>(private val content: T) {

    //были ли переданы данные
    private var hasBeenHandled = false

    /** Returns the content and prevents its use again. */
    fun getContentIfNotHandled(): T? {
        Timber.d("getContentIfNotHandled")

        return  if (hasBeenHandled){
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}