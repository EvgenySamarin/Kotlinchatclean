package ru.dvc.kotlin_chat_clean.accout

import ru.dvc.kotlin_chat_clean.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.type.None
import javax.inject.Inject

/** UseCase, для выполнения обновления токена. */
class UpdateToken @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, UpdateToken.Params>() {

    override suspend fun run(params: Params) = accountRepository.updateAccountToken(params.token)

    data class Params(val token: String)
}