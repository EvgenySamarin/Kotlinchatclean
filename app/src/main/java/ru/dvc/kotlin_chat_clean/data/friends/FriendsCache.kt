package ru.dvc.kotlin_chat_clean.data.friends

import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity

/** содержит функции для взаимодействия с друзьями в БД */
interface FriendsCache {
    /** @since 20200402 v1: сохраняет друга в бд. */
    fun saveFriend(entity: FriendEntity)

    /** @since 20200402 v1: получает друга из бд по идентификатору. */
    fun getFriend(key: Long): FriendEntity?

    /** @since 20200402 v1: получает список друзей текущего пользователя */
    fun getFriends(): List<FriendEntity>

    /** @since 20200402 v1: получает список входящих приглашений на добавление в друзья */
    fun getFriendRequests(): List<FriendEntity>

    /** @since 20200402 v1: удаляет друга из бд по идентификатору. */
    fun removeFriendEntity(key: Long)
}