package ru.dvc.kotlin_chat_clean.data.remote.account

import ru.dvc.kotlin_chat_clean.data.remote.core.BaseResponse
import ru.dvc.kotlin_chat_clean.domain.accout.AccountEntity

class AuthResponse(
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)