package ru.dvc.kotlin_chat_clean.domain.friends

import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.None
import timber.log.Timber
import javax.inject.Inject

class ApproveFriendRequest @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, FriendEntity>() {

    override suspend fun run(params: FriendEntity): Either<Failure, None> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")

        return friendsRepository.approveFriendRequest(params)
    }
}