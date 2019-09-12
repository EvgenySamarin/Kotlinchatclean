package ru.dvc.kotlin_chat_clean.data.remote.friends

import com.google.gson.annotations.SerializedName
import ru.dvc.kotlin_chat_clean.data.remote.core.BaseResponse
import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity

class GetFriendRequestsResponse (
    success: Int,
    message: String,
    @SerializedName("friend_requests")
    val friendsRequests: List<FriendEntity>
) : BaseResponse(success, message)