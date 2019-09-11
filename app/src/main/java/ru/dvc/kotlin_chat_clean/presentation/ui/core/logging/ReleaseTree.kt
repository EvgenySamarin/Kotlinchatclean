package ru.dvc.kotlin_chat_clean.presentation.ui.core.logging

import android.content.Context
import android.util.Log.ERROR
import android.util.Log.WARN
import android.widget.Toast
import ru.dvc.kotlin_chat_clean.R
import timber.log.Timber
import javax.inject.Inject


/**
 *
 *
 * @author EY.Samarin
 */
class ReleaseTree @Inject constructor(private val context: Context) : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == ERROR || priority == WARN)
            Toast.makeText(
                context,
                context.resources.getString(R.string.ERROR_LOG_WTF),
                Toast.LENGTH_SHORT
            ).show()
    }

}