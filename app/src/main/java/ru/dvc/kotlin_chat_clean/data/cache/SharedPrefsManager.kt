package ru.dvc.kotlin_chat_clean.data.cache

import android.content.SharedPreferences
import ru.dvc.kotlin_chat_clean.domain.accout.AccountEntity
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.None
import timber.log.Timber
import javax.inject.Inject

/**
 * Класс для работы с SharedPreferences. Содержит: объект SharedPreferences(val prefs),
 * вспомогательные константы(val ACCOUNT_TOKEN), функции для редактирования(fun saveToken(…))
 * и получеpния(fun getToken()) токена из SharedPreferences.
 *
 * Сохранение и восстановление данных.
 */
class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_TOKEN = "account_token"
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_NAME = "account_name"
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_STATUS = "account_status"
        const val ACCOUNT_DATE = "account_date"
        const val ACCOUNT_IMAGE = "account_image"
        const val ACCOUNT_PASSWORD = "account_password"
    }

    /** записываем токен в хранилку */
    fun saveToken(token: String): Either<Failure, None> {
        Timber.d("saveToken")

        prefs.edit().apply {
            putString(ACCOUNT_TOKEN, token)
        }.apply()

        return Either.Right(None())
    }

    /** получаем токен */
    fun getToken(): Either<Failure, String> {
        Timber.d("getToken")

        return Either.Right(prefs.getString(ACCOUNT_TOKEN, "") ?: "")
    }

    fun saveAccount(account: AccountEntity): Either<Failure, None> {
        Timber.d("saveAccount")

        prefs.edit().apply {
            putSafely(ACCOUNT_ID, account.id)
            putSafely(ACCOUNT_NAME, account.name)
            putSafely(ACCOUNT_EMAIL, account.email)
            putSafely(ACCOUNT_TOKEN, account.token)
            putString(ACCOUNT_STATUS, account.status)
            putSafely(ACCOUNT_DATE, account.userDate)
            putSafely(ACCOUNT_IMAGE, account.image)
            putSafely(ACCOUNT_PASSWORD, account.password)
        }.apply()

        return Either.Right(None())
    }

    fun getAccount(): Either<Failure, AccountEntity> {
        Timber.d("getAccount")

        val id = prefs.getLong(ACCOUNT_ID, 0)

        if (id == 0L) {
            return Either.Left(Failure.NoSavedAccountsError)
        }

        val account = AccountEntity(
            prefs.getLong(ACCOUNT_ID, 0),
            prefs.getString(ACCOUNT_NAME, "")!!,
            prefs.getString(ACCOUNT_EMAIL, "")!!,
            prefs.getString(ACCOUNT_TOKEN, "")!!,
            prefs.getString(ACCOUNT_STATUS, "")!!,
            prefs.getLong(ACCOUNT_DATE, 0),
            prefs.getString(ACCOUNT_IMAGE, "")!!,
            prefs.getString(ACCOUNT_PASSWORD,"")!!
        )

        return Either.Right(account)
    }

    fun removeAccount(): Either<Failure, None> {
        Timber.d("removeAccount")

        prefs.edit().apply {
            remove(ACCOUNT_ID)
            remove(ACCOUNT_NAME)
            remove(ACCOUNT_EMAIL)
            remove(ACCOUNT_STATUS)
            remove(ACCOUNT_DATE)
            remove(ACCOUNT_IMAGE)
            remove(ACCOUNT_PASSWORD)
        }.apply()

        return Either.Right(None())
    }

    fun containsAnyAccount(): Boolean {
        val id = prefs.getLong(ACCOUNT_ID, 0)
        Timber.d("containsAnyAccount: $id")
        return id != 0L
    }
}

/** 2019.11.13 v1 типобезопастное помещение в sharedpreference */
fun SharedPreferences.Editor.putSafely(key: String, value: Long?) {
    if (value != null && value != 0L) {
        putLong(key, value)
    }
}

/** 2019.11.13 v1 типобезопастное помещение в sharedpreference */
fun SharedPreferences.Editor.putSafely(key: String, value: String?) {
    if (value != null && value.isNotEmpty()) {
        putString(key, value)
    }
}