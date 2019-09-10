package ru.dvc.kotlin_chat_clean.presentation.ui.core.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ru.dvc.kotlin_chat_clean.presentation.Authenticator
import ru.dvc.kotlin_chat_clean.presentation.ui.home.HomeActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.login.LoginActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.register.RegisterActivity
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Вспомогательный класс. для запуска активити
 */
@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    /**
     * в зависимости от наличия авторизованной сессии, запускает HomeActivity (если true) или
     * LoginActivity (если false). Принимает Context. Ничего не возвращает
     */
    fun showMain(context: Context) {
        Timber.d("showMain")

        when (authenticator.userLoggedIn()) {
            true -> showHome(context, false)
            false -> showLogin(context, false)
        }
    }

    /**
     * запускает LoginActivity. Принимает Context, Boolean: newTask – определяет,
     * очищать(если true), или не очищать(если false) backstack приложения. Ничего не возвращает.
     */
    fun showLogin(context: Context, newTask: Boolean = true) {
        Timber.d("showLogin")

        context.startActivity<LoginActivity>(newTask = newTask)
    }

    /** запускает Принимает Context, Boolean: newTask. Ничего не возвращает */
    fun showHome(context: Context, newTask: Boolean = true) {
        Timber.d("showHome")

        context.startActivity<HomeActivity>(newTask = newTask)
    }

    /** запускает Принимает Context. Ничего не возвращает. */
    fun showSignUp(context: Context) {
        Timber.d("showSignUp")

        context.startActivity<RegisterActivity>()
    }
}

/**
 * вспомогательная extension функция, которая запускает activity. Формирует intent, при
 * необходимости передает в него флаги очищения стека (addFlags(…)) и аргументы (putExtra(…)).
 * Присутствует параметризированный вещественный тип T, который представляет из себя класс activity.
 * Принимает Boolean: newTask; Bundle: args.
 */
private inline fun <reified T> Context.startActivity(
    newTask: Boolean = false,
    args: Bundle? = null
) {
    Timber.d("startActivity")

    this.startActivity(Intent(this, T::class.java).apply {
        if (newTask) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        putExtra("args", args)
    })
}
