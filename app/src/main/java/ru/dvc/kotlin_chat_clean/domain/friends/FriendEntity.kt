package ru.dvc.kotlin_chat_clean.domain.friends

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * DTO to save exist friends info
 * @param isRequest переменная для хранения типа данных (запрос в друзья = 1, друг = 0)
 *
 * @author EvgenySamarin
 * @since 20200312 v1
 */
@Entity(tableName = "friends_table")
class FriendEntity(
    @PrimaryKey
    @SerializedName("user_id")
    var id: Long,
    var name: String,
    var email: String,
    @ColumnInfo(name = "friends_id")
    @SerializedName("friends_id")
    var friendsId: Long,
    var status: String,
    var image: String,
    @ColumnInfo(name = "is_request")
    var isRequest: Int = 0
)