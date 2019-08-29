package ru.dvc.kotlin_chat_clean.data.remote.core

import retrofit2.Call
import retrofit2.Response
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Класс, выполняющий сетевые запросы. Содержит: объект для проверки подключения (val
 * networkHandler), функции для выполнения запроса(fun execute(…)) и проверки ответа(extension fun
 * Response.isSucceed()).
 *
 * Для выполнения сетевых запросов и проверки ответа сервера.
 *
 * @author EY.Samarin
 */
@Singleton
class Request @Inject constructor(private val networkHandler: NetworkHandler) {

    /** вспомогательная функция для проверки сети и вызова fun execute */
    fun <T : BaseResponse, R> make(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }

    /**
     * выполняет сетевой запрос с помощью переданного в параметрах call (call.execute()).
     * В блоке catch формирует маркеры ошибок для дальнейшей обработки
     * (Either.Left(Failure.ServerError)). Функция имеет параметризированные типы:
     * T(наследуемый от BaseResponse) и R.
     * Принимает Call и функцию высшего порядка для трансформации
     * transform(принимает T, возвращает R). Возвращает Either<Failure, R>.
     */
    private fun <T : BaseResponse, R> execute(
        call: Call<T>,
        transform: (T) -> R
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSucceed()) {
                true -> Either.Right(transform((response.body()!!)))
                false -> Either.Left(response.parseError())
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}

fun <T : BaseResponse> Response<T>.isSucceed(): Boolean {
    return isSuccessful && body() != null && (body() as BaseResponse).success == 1
}

fun <T : BaseResponse> Response<T>.parseError(): Failure {
    val message = (body() as BaseResponse).message
    return when (message) {
        "email already exists" -> Failure.EmailAlreadyExistError
        else -> Failure.ServerError
    }
}