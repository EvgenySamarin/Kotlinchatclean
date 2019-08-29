package ru.dvc.kotlin_chat_clean.data.cache

import ru.dvc.kotlin_chat_clean.data.account.AccountCache
import ru.dvc.kotlin_chat_clean.domain.accout.AccountEntity
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.None
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import javax.inject.Inject

/**
 * Класс, содержащий функции взаимодействия с аккаунтом в бд. Содержит: объект для работы с
 * SharedPrefsManager(val prefsManager), функции которые выполняют сохранение(fun saveToken(…))
 * и восстановление(fun getToken()) токена.
 *
 * Для взаимодействия с аккаунтом в бд.
 */
class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager) :
    AccountCache {

    override fun saveToken(token: String): Either<Failure, None> {
        return prefsManager.saveToken(token)
    }

    override fun logout(): Either<Failure, None> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveAccount(account: AccountEntity): Either<Failure, None> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getToken(): Either<Failure, String> {
        return prefsManager.getToken()
    }
}