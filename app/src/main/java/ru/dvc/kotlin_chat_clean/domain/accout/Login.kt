package ru.dvc.kotlin_chat_clean.domain.accout

import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import timber.log.Timber
import javax.inject.Inject

/** UseCase */
class Login @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, Login.Params>() {

    override suspend fun run(params: Params): Either<Failure, AccountEntity> {
        Timber.d("run")

        return accountRepository.login(params.email, params.password)
    }

    data class Params(val email: String, val password: String)
}