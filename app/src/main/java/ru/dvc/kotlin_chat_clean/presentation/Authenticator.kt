package ru.dvc.kotlin_chat_clean.presentation

import ru.dvc.kotlin_chat_clean.data.cache.SharedPrefsManager
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * При запуске приложения нужно проверить, авторизован ли пользователь. Authenticator проверяет
 * наличие незавершенных сессий.
 */
@Singleton
class Authenticator
@Inject constructor(
    val sharedPrefsManager: SharedPrefsManager
) {
    fun userLoggedIn(): Boolean {
        Timber.d("userLoggedIn")

        return sharedPrefsManager.containsAnyAccount()
    }
}