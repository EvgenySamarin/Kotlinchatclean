package ru.dvc.kotlin_chat_clean.domain.accout

import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.None
import javax.inject.Inject

/** UseCase, для выполнения обновления токена. */
class UpdateToken @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, UpdateToken.Params>() {

    override suspend fun run(params: Params) = accountRepository.updateAccountToken(params.token)

    data class Params(val token: String)
}