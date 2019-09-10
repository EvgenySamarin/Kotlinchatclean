package ru.dvc.kotlin_chat_clean.data.account

import ru.dvc.kotlin_chat_clean.domain.accout.AccountEntity
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.None
import ru.dvc.kotlin_chat_clean.domain.type.Failure

/**
 * Интерфейс, содержащий функции для взаимодействия с аккаунтом в локальной базе данных.
 *
 * @author EY.Samarin
 */
interface AccountCache {
    /** возвращает токен из локальной базы данных */
    fun getToken(): Either<Failure, String>
    /**  выполняет сохранение токена в локальную базу данных */
    fun saveToken(token: String): Either<Failure, None>

    fun logout(): Either<Failure, None>

    fun getCurrentAccount(): Either<Failure, AccountEntity>
    fun saveAccount(account: AccountEntity): Either<Failure, None>
}