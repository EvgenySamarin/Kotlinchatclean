package ru.dvc.kotlin_chat_clean.data.remote.friends

import ru.dvc.kotlin_chat_clean.data.remote.core.BaseResponse
import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity

class GetFriendsResponse (
    success: Int,
    message: String,
    val friends: List<FriendEntity>
) : BaseResponse(success, message)