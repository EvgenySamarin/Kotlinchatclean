package ru.dvc.kotlin_chat_clean.cache

import android.content.SharedPreferences
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.None
import ru.dvc.kotlin_chat_clean.domain.type.exception.Failure
import javax.inject.Inject

/**
 * Класс для работы с SharedPreferences. Содержит: объект SharedPreferences(val prefs),
 * вспомогательные константы(val ACCOUNT_TOKEN), функции для редактирования(fun saveToken(…))
 * и получения(fun getToken()) токена из SharedPreferences.
 *
 * Сохранение и восстановление данных.
 */
class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_TOKEN = "account_token"
    }

    /** записываем токен в хранилку */
    fun saveToken(token: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }


    fun getToken(): Either<Failure, String> {
        return Either.Right(prefs.getString(ACCOUNT_TOKEN, "") ?: "")
    }
}