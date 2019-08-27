package ru.dvc.kotlin_chat_clean.remote.account

import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import ru.dvc.kotlin_chat_clean.data.account.AccountRemote
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.None
import ru.dvc.kotlin_chat_clean.domain.type.exception.Failure
import ru.dvc.kotlin_chat_clean.remote.core.Request
import ru.dvc.kotlin_chat_clean.remote.service.ApiService
import javax.inject.Inject

/**
 * Класс, содержащий функции взаимодействия с аккаунтом в сети. Содержит: объект для создания
 * сетевых запросов(val request), API сервис(val service), функции для выполнения
 * регистрации(fun register(…)) и map’а параметров запроса(fun createRegisterMap(…)).
 */
class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : AccountRemote {

    override fun register(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Either<Failure, None> {
        return request.make(service.register(createRegisterMap(email, name, password, token, userDate))) { None() }
    }

    private fun createRegisterMap(
        email: String,
        name: String,
        password: String,
        token: String,
        userDate: Long
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_NAME, name)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_USER_DATE, userDate.toString())
        return map
    }
}