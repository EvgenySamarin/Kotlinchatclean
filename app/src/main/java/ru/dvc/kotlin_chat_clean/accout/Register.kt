package ru.dvc.kotlin_chat_clean.accout

import ru.dvc.kotlin_chat_clean.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.type.Either
import ru.dvc.kotlin_chat_clean.type.None
import ru.dvc.kotlin_chat_clean.type.exception.Failure
import javax.inject.Inject

/**
 * UseCase. Содержит: объект репозитория(val repository), класс для хранения данных(class Params),
 * имплементированную функцию выполнения(fun run(…)). Нужен для выполнения регистрации
 */
class Register @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, Register.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        return repository.register(params.email, params.name, params.password)
    }

    data class Params(val email: String, val name: String, val password: String)
}