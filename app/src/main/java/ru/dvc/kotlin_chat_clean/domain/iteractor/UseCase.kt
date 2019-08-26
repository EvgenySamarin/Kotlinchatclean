package ru.dvc.kotlin_chat_clean.domain.iteractor

import kotlinx.coroutines.*
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.exception.Failure
import kotlin.coroutines.CoroutineContext


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 *
 * @param Params тип класса-оболочки, хранящей параметры для выполнения функции
 * @param Type тип возвращаемого объекта с данными
 *
 */
abstract class UseCase<out Type, in Params> {
    //создает объект фонового контекста для выполнения работ.
    var backgroundContext: CoroutineContext = Dispatchers.IO
    //создает объект UI контекста для выполнения работ.
    var foregroundContext: CoroutineContext = Dispatchers.Main
    //создает объект, используемый для инициирования и отмены работы.
    private var parentJob: Job = Job()

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        unsubscribe()
        parentJob = Job()

        CoroutineScope(foregroundContext + parentJob).launch {
            val result = withContext(backgroundContext) {
                run(params)
            }

            onResult(result)
        }
    }

    fun unsubscribe(){
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }
}
