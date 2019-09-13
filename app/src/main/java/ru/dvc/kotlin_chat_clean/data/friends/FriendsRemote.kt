package ru.dvc.kotlin_chat_clean.data.friends

import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.None

/** Содержит функции для взаимодействия с друзьями на сервере */
interface FriendsRemote {
    /** получение списка друзей текущего пользователя */
    fun getFriends(userId: Long, token: String): Either<Failure, List<FriendEntity>>

    /** получение списка входящих приглашений на добавление в друзья */
    fun getFriendRequests(userId: Long, token: String): Either<Failure, List<FriendEntity>>

    /** принимает приглашение на добавление в друзья */
    fun approveFriendRequest(userId: Long, requestUserId: Long, friendsId: Long, token: String): Either<Failure, None>

    /** отклоняет приглашение на добавление в друзья */
    fun cancelFriendRequest(userId: Long, requestUserId: Long, friendsId: Long, token: String): Either<Failure, None>

    /** высылает приглашение на добавление в друзья */
    fun addFriend(email: String, userId: Long, token: String): Either<Failure, None>

    /** удаляет из друзей */
    fun deleteFriend(userId: Long, requestUserId: Long, friendsId: Long, token: String): Either<Failure, None>
}