package ru.dvc.kotlin_chat_clean.remote.service

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.dvc.kotlin_chat_clean.remote.core.BaseResponse


/**
 * Интерфейс с функциями API. Содержит: вспомогательные константы с именем метода и параметрами,
 * функцию для регистрации(fun register(…)).
 *
 * Для формирования API запросов.
 *
 * @author EY.Samarin
 */
interface ApiService {
    companion object {
        //methods
        const val REGISTER = "register.php"

        //params
        const val PARAM_EMAIL = "email"
        const val PARAM_PASSWORD = "password"
        const val PARAM_NAME = "name"
        const val PARAM_TOKEN = "token"
        const val PARAM_USER_DATE = "user_date"
    }

    @FormUrlEncoded
    @POST(REGISTER)
    fun register(@FieldMap params: Map<String, String>): Call<BaseResponse>
}
