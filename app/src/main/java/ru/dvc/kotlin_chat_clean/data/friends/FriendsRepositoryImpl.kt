package ru.dvc.kotlin_chat_clean.data.friends

import ru.dvc.kotlin_chat_clean.data.account.AccountCache
import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity
import ru.dvc.kotlin_chat_clean.domain.friends.FriendsRepository
import ru.dvc.kotlin_chat_clean.domain.type.*
import timber.log.Timber

class FriendsRepositoryImpl(
    private val accountCache: AccountCache,
    private val friendsRemote: FriendsRemote,
    private val friendsCache: FriendsCache
) : FriendsRepository {

    override fun getFriends(needFetch: Boolean): Either<Failure, List<FriendEntity>> {
        Timber.d("execute fun: ${object {}.javaClass.enclosingMethod?.name}")

        return accountCache.getCurrentAccount()
            /** Функция flatMap по сути использует данные полученные при помощи фукнции и использует
            их внутри себя а вернуть она может тип который мы уже умеем обрабатывать */
            .flatMap {
                return@flatMap if (needFetch) {
                    friendsRemote.getFriends(it.id, it.token)
                } else
                    Either.Right(friendsCache.getFriends())

            }.onNext {
                /** @since 20200402 v1: map применяет функцию к каждому элементу списка */
                it.map {
                    friendsCache.saveFriend(it)
                }
            }
    }

    override fun getFriendRequests(needFetch: Boolean): Either<Failure, List<FriendEntity>> {
        return accountCache.getCurrentAccount()
            .flatMap {
                return@flatMap if (needFetch)
                    friendsRemote.getFriendRequests(it.id, it.token)
                else Either.Right(friendsCache.getFriendRequests())
            }.onNext { listFriends ->
                listFriends.map {
                    it.isRequest = 1
                    friendsCache.saveFriend(it)
                }
            }
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
            }.onNext {
                friendEntity.isRequest = 0
                friendsCache.saveFriend(friendEntity)
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
            }.onNext {
                // выполняется удаление приглашения в друзья из бд.
                friendsCache.removeFriendEntity(friendEntity.id)
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
            }.onNext {
                friendsCache.removeFriendEntity(friendEntity.id)
            }
    }
}