package ru.dvc.kotlin_chat_clean.presentation.ui.register

import android.os.Bundle
import ru.dvc.kotlin_chat_clean.presentation.ui.core.BaseActivity
import timber.log.Timber

/**
 *
 * @author EY.Samarin
 */
class RegisterActivity : BaseActivity() {
    override val fragment = RegisterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")

        super.onCreate(savedInstanceState)
    }
}