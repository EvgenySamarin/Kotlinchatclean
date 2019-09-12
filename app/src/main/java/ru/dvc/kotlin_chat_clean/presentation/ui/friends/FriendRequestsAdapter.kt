package ru.dvc.kotlin_chat_clean.presentation.ui.friends

import android.view.View
import kotlinx.android.synthetic.main.item_friend_request.view.*
import ru.dvc.kotlin_chat_clean.R
import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity
import ru.dvc.kotlin_chat_clean.presentation.ui.core.BaseAdapter

/** Для отображения списка приглашений дружбы */
open class FriendRequestsAdapter : BaseAdapter<FriendRequestsAdapter.FriendRequestViewHolder>() {
    /** переопределение id макета элемента списка */
    override val layoutRes = R.layout.item_friend_request

    override fun createHolder(view: View, viewType: Int): FriendRequestViewHolder {
        return FriendRequestViewHolder(view)
    }

    /** Для отображения элемента приглашения в друзья. */
    class FriendRequestViewHolder(view: View) : BaseViewHolder(view) {

        init {
            view.btnApprove.setOnClickListener {
                onClick?.onClick(item, it)
            }
            view.btnCancel.setOnClickListener {
                onClick?.onClick(item, it)
            }
        }

        /** заполнение макета данными */
        override fun onBind(item: Any) {
            (item as? FriendEntity)?.let {
                view.tvName.text = it.name
            }

        }
    }
}