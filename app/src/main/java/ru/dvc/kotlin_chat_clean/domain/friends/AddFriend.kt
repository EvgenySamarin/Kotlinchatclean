package ru.dvc.kotlin_chat_clean.domain.friends

import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.None
import timber.log.Timber
import javax.inject.Inject

/**
 * Для отправления приглашения дружбы
 * @param friendsRepository обработчик UseCase взаимодействует с репозиториями
 */
class AddFriend @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, AddFriend.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")

        return friendsRepository.addFriend(params.email)
    }

    /** содержит поля для передачи параметров в запросе к репозиторию*/
    data class Params(val email: String)
}