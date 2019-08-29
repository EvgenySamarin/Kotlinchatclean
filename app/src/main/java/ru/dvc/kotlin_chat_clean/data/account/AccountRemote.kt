package ru.dvc.kotlin_chat_clean.data.account

import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.None
import ru.dvc.kotlin_chat_clean.domain.type.Failure

/** Интерфейс, содержащий функции для взаимодействия с аккаунтом на сервере. */
interface AccountRemote {
    fun register(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Either<Failure, None>
}