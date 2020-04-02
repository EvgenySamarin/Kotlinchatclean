package ru.dvc.kotlin_chat_clean.domain.friends

import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import javax.inject.Inject

/** Для получения списка входящих приглашения дружбы */
class GetFriendRequests @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<List<FriendEntity>, Boolean>() {

    /** @since 20200312 v1: вызывает или запрос к БД или к сети в зависимости от параметра */
    override suspend fun run(params: Boolean): Either<Failure, List<FriendEntity>> {
        return friendsRepository.getFriendRequests(params)
    }
}