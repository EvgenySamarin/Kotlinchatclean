package ru.dvc.kotlin_chat_clean.domain.type.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Класс нужен для передачи маркера об ошибке с дальнейшей ее обработкой.
 *
 * Пример:
 *
 * API запросы подразумевают риск получения ошибок: сервер не доступен, нет подключения к сети.
 * При их получении мы искусственно создаем объекты ошибок, которые можно удобно обработать
 * (Выводить разные тосты в зависимости от типа объекта).
 *
 * @author EY.Samarin
 */
sealed class Failure {
    object  NetworkConnectionError: Failure()
    object  ServerError: Failure()
}