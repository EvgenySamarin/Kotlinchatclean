package ru.dvc.kotlin_chat_clean.data.friends

import ru.dvc.kotlin_chat_clean.data.account.AccountCache
import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity
import ru.dvc.kotlin_chat_clean.domain.friends.FriendsRepository
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.None
import ru.dvc.kotlin_chat_clean.domain.type.flatMap
import timber.log.Timber

class FriendsRepositoryImpl(
    private val accountCache: AccountCache,
    private val friendsRemote: FriendsRemote
) : FriendsRepository {

    override fun getFriends(needFetch: Boolean): Either<Failure, List<FriendEntity>> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")

        return accountCache.getCurrentAccount()
            /** Функция flatMap по сути использует данные полученные при помощи фукнции и использует
              их внутри себя а вернуть она может тип который мы уже умеем обрабатывать */
            .flatMap { friendsRemote.getFriends(it.id, it.token) }
    }

    override fun getFriendRequests(needFetch: Boolean): Either<Failure, List<FriendEntity>> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.getFriendRequests(it.id, it.token) }
    }

    override fun approveFriendRequest(friendEntity: FriendEntity): Either<Failure, None> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")

        return accountCache.getCurrentAccount()
            .flatMap {
                friendsRemote.approveFriendRequest(
                    it.id,
                    friendEntity.id,
                    friendEntity.friendsId,
                    it.token
                )
            }
    }

    override fun cancelFriendRequest(friendEntity: FriendEntity): Either<Failure, None> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")
        return accountCache.getCurrentAccount()
            .flatMap {
                friendsRemote.cancelFriendRequest(
                    it.id,
                    friendEntity.id,
                    friendEntity.friendsId,
                    it.token
                )
            }
    }

    override fun addFriend(email: String): Either<Failure, None> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")
        return accountCache.getCurrentAccount()
            .flatMap { friendsRemote.addFriend(email, it.id, it.token) }
    }

    override fun deleteFriend(friendEntity: FriendEntity): Either<Failure, None> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")
        return accountCache.getCurrentAccount()
            .flatMap {
                friendsRemote.deleteFriend(
                    it.id,
                    friendEntity.id,
                    friendEntity.friendsId,
                    it.token
                )
            }
    }
}