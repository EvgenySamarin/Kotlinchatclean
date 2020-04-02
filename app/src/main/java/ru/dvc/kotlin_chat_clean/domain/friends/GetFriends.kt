package ru.dvc.kotlin_chat_clean.domain.friends

import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import javax.inject.Inject

/** Для получения списка друзей */
class GetFriends @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<List<FriendEntity>, Boolean>() {

    /** @since 20200312 v1: вызывает или запрос к базе данных, или запрос к серверу в сети в
     *  зависимости от параметра */
    override suspend fun run(params: Boolean): Either<Failure, List<FriendEntity>> {
        return friendsRepository.getFriends(params)
    }
}