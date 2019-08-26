package ru.dvc.kotlin_chat_clean.accout

import ru.dvc.kotlin_chat_clean.type.Either
import ru.dvc.kotlin_chat_clean.type.None
import ru.dvc.kotlin_chat_clean.type.exception.Failure

/** Интерфейс, содержащий функции для взаимодействия с аккаунтом */
interface AccountRepository {
    fun login(email: String, password: String): Either<Failure, AccountEntity>
    fun logout(): Either<Failure, None>
    fun register(email: String, name: String, password: String): Either<Failure, None>
    fun forgetPassword(email: String): Either<Failure, None>

    fun getCurrentAccount(): Either<Failure, AccountEntity>

    fun updateAccountToken(token: String): Either<Failure, None>
    fun updateAccountLastSeen(): Either<Failure, None>

    fun editAccount(entity: AccountEntity): Either<Failure, AccountEntity>
}