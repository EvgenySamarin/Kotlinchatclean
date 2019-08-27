package ru.dvc.kotlin_chat_clean.remote.service

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import ru.dvc.kotlin_chat_clean.remote.core.BaseResponse
import java.util.LinkedHashMap


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
