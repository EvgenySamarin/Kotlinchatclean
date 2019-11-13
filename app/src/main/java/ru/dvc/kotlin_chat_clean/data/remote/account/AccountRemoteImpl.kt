package ru.dvc.kotlin_chat_clean.data.remote.account

import ru.dvc.kotlin_chat_clean.data.account.AccountRemote
import ru.dvc.kotlin_chat_clean.data.remote.core.Request
import ru.dvc.kotlin_chat_clean.data.remote.service.ApiService
import ru.dvc.kotlin_chat_clean.domain.accout.AccountEntity
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.None
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

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
        email: String, name: String, password: String, token: String, userDate: Long
    ): Either<Failure, None> {
        Timber.d("register")

        return request.make(
            service.register(createRegisterMap(email, name, password, token, userDate))
        ) { None() }
    }

    private fun createRegisterMap(
        email: String, name: String, password: String, token: String, userDate: Long
    ): Map<String, String> {
        Timber.d("createRegisterMap")

        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_NAME, name)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_USER_DATE, userDate.toString())
        return map
    }

    override fun login(
        email: String, password: String, token: String
    ): Either<Failure, AccountEntity> {
        Timber.d("login")

        return request.make(service.login(createLoginMap(email, password, token))) { it.user }
    }

    override fun updateToken(userId: Long, token: String, oldToken: String): Either<Failure, None> {
        Timber.d("updateToken")

        return request.make(
            service.updateToken(createUpdateTokenMap(userId, token, oldToken))
        ) { None() }
    }

    /** 2019.11.13 v1 выполняет изменение данных пользователя на сервере */
    override fun editUser(
        userId: Long,
        email: String,
        name: String,
        password: String,
        status: String,
        token: String,
        image: String
    ): Either<Failure, AccountEntity> {
        return request.make(service.editUser(createUserEditMap(userId, email, name,
            password, status, token, image))) { it.user }
    }

    private fun createLoginMap(
        email: String, password: String, token: String
    ): Map<String, String> {
        Timber.d("createLoginMap")

        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }

    private fun createUpdateTokenMap(
        userId: Long, token: String, oldToken: String
    ): Map<String, String> {
        Timber.d("createUpdateTokenMap")

        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        map.put(ApiService.PARAM_OLD_TOKEN, oldToken)
        return map
    }

    /** 2019.11.13 v1 вспомогательная функция создающая map для данных пользователя */
    private fun createUserEditMap(
        id: Long,
        email: String,
        name: String,
        password: String,
        status: String,
        token: String,
        image: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, id.toString())
        map.put(ApiService.PARAM_EMAIL, email)
        map.put(ApiService.PARAM_NAME, name)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_STATUS, status)
        map.put(ApiService.PARAM_TOKEN, token)
        if (image.startsWith("../")) {
            map.put(ApiService.PARAM_IMAGE_UPLOADED, image)
        } else {
            map.put(ApiService.PARAM_IMAGE_NEW, image)
            map.put(ApiService.PARAM_IMAGE_NEW_NAME, "user_${id}_${Date().time}_photo")
        }
        return map
    }
}