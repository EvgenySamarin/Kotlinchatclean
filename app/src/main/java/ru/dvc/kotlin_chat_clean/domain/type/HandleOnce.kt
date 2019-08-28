package ru.dvc.kotlin_chat_clean.domain.type


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
        return  if (hasBeenHandled){
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}