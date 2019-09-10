package ru.dvc.kotlin_chat_clean.domain.accout

import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.None
import javax.inject.Inject

class GetAccount @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, None>() {

    override suspend fun run(params: None) = accountRepository.getCurrentAccount()
}