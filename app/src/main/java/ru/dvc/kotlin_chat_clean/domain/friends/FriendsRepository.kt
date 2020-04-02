package ru.dvc.kotlin_chat_clean.domain.friends

import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.None

interface FriendsRepository {
    /** получение списка друзей текущего пользователя */
    fun getFriends(needFetch: Boolean): Either<Failure, List<FriendEntity>>

    /**получение списка входящих приглашений на добавление в друзья*/
    fun getFriendRequests(needFetch: Boolean): Either<Failure, List<FriendEntity>>

    /** принимает приглашение на добавление в друзья */
    fun approveFriendRequest(friendEntity: FriendEntity): Either<Failure, None>

    /** отклоняет приглашение на добавление в друзья */
    fun cancelFriendRequest(friendEntity: FriendEntity): Either<Failure, None>

    /** высылает приглашение на добавление в друзья */
    fun addFriend(email: String): Either<Failure, None>

    /** удаляет из друзей */
    fun deleteFriend(friendEntity: FriendEntity): Either<Failure, None>
}