package ru.dvc.kotlin_chat_clean.data.remote.service

import retrofit2.Call
import retrofit2.http.*
import ru.dvc.kotlin_chat_clean.data.remote.account.AuthResponse
import ru.dvc.kotlin_chat_clean.data.remote.core.BaseResponse
import ru.dvc.kotlin_chat_clean.data.remote.friends.GetFriendRequestsResponse
import ru.dvc.kotlin_chat_clean.data.remote.friends.GetFriendsResponse


/**
 * Интерфейс с функциями API. Содержит: вспомогательные константы с именем метода и параметрами,
 * функцию для регистрации(fun register(…)).
 *
 * Для формирования API запросов.
 *
 * @author EY.Samarin
 */
interface ApiService {
    companion object {
        //methods
        const val REGISTER = "register.php"
        const val LOGIN = "login.php"
        const val UPDATE_TOKEN = "updateUserToken.php"
        const val ADD_FRIEND = "addFriend.php"
        const val APPROVE_FRIEND_REQUEST = "approveFriendRequest.php"
        const val CANCEL_FRIEND_REQUEST = "cancelFriendRequest.php"
        const val DELETE_FRIEND = "deleteFriend.php"
        const val GET_FRIENDS = "getContactsByUser.php"
        const val GET_FRIEND_REQUESTS = "getFriendRequestsByUser.php"

        //params
        const val PARAM_EMAIL = "email"
        const val PARAM_PASSWORD = "password"
        const val PARAM_NAME = "name"
        const val PARAM_TOKEN = "token"
        const val PARAM_USER_DATE = "user_date"
        const val PARAM_USER_ID = "user_id"
        const val PARAM_OLD_TOKEN = "old_token"
        const val PARAM_REQUEST_USER_ID = "request_user_id"
        const val PARAM_FRIENDS_ID = "friends_id"
        const val PARAM_STATUS = "status"
        const val PARAM_REQUEST_USER = "request_user"
        const val PARAM_APPROVED_USER = "approved_user"
    }

    @FormUrlEncoded
    @POST(REGISTER)
    fun register(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(LOGIN)
    fun login(@FieldMap params: Map<String, String>): Call<AuthResponse>

    @FormUrlEncoded
    @POST(UPDATE_TOKEN)
    fun updateToken(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(ADD_FRIEND)
    fun addFriend(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(APPROVE_FRIEND_REQUEST)
    fun approveFriendRequest(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(CANCEL_FRIEND_REQUEST)
    fun cancelFriendRequest(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(DELETE_FRIEND)
    fun deleteFriend(@FieldMap params: Map<String, String>): Call<BaseResponse>

    @FormUrlEncoded
    @POST(GET_FRIENDS)
    fun getFriends(@FieldMap params: Map<String, String>): Call<GetFriendsResponse>

    @FormUrlEncoded
    @POST(GET_FRIEND_REQUESTS)
    fun getFriendRequests(@FieldMap params: Map<String, String>): Call<GetFriendRequestsResponse>
}
