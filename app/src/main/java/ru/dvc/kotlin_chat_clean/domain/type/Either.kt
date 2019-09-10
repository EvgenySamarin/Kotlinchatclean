package ru.dvc.kotlin_chat_clean.domain.type

import ru.dvc.kotlin_chat_clean.domain.type.Either.Left
import ru.dvc.kotlin_chat_clean.domain.type.Either.Right
import timber.log.Timber

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [Left] or [Right].
 * FP Convention dictates that [Left] is used for "failure"
 * and [Right] is used for "success".
 * Для передачи одного из двух возможных типов данных, неизвестного в момент компиляции,
 * но известного в момент выполнения(кот Шредингера).
 *
 * @see Left
 * @see Right
 */
sealed class Either<out L, out R> {
    /** Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /** Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    /** Выполняет одну из двух переданных функций высшего порядка переданных в параметре */
    fun either(functionLeft: (L) -> Any, functionRight: (R) -> Any): Any {
        Timber.d("either")

        return when (this) {
            is Left -> functionLeft(a)
            is Right -> functionRight(b)
        }
    }
}

fun <A, B, C> ((A) -> B).compose(f: (B) -> C): (A) -> C {
    Timber.d(".compose")

    return {
        f(this(it))
    }
}


/**
 * выполняет преобразование.
 * если объект Either является типом L (error) – возвращает его без изменений;
 * если объект Either является типом R (right) – с помощью переданной ф-ции высшего порядка
 * (fn: (R) -> Either<L, T>) подменяет исходный Either другим Either, преобразовывая его содержимое
 * (R)
 */
fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> {
    Timber.d(".flatMap")

    return when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }
}

/** Выполняет преобразование fn если объект является типом R, L возвращается без изменений */
fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> {
    Timber.d(".map")

    return this.flatMap(fn.compose(::right))
}

/** выполняет функию над объектом, когда она R  */
fun <L, R> Either<L, R>.onNext(fn: (R) -> Unit): Either<L, R> {
    Timber.d(".onNext")

    this.flatMap(fn.compose(::right))
    return this
}
