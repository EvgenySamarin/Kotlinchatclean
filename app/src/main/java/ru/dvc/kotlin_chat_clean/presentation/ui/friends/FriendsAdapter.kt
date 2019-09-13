package ru.dvc.kotlin_chat_clean.presentation.ui.friends

import android.view.View
import kotlinx.android.synthetic.main.item_friend.view.*
import ru.dvc.kotlin_chat_clean.R
import ru.dvc.kotlin_chat_clean.domain.friends.FriendEntity
import ru.dvc.kotlin_chat_clean.presentation.ui.core.BaseAdapter
import timber.log.Timber

open class FriendsAdapter : BaseAdapter<FriendsAdapter.FriendViewHolder>() {
    override val layoutRes = R.layout.item_friend

    override fun createHolder(view: View, viewType: Int): FriendViewHolder {
        return FriendViewHolder(view)
    }

    class FriendViewHolder(view: View) : BaseViewHolder(view) {

        init {
            view.btnRemove.setOnClickListener {
                Timber.d("click btnRemove")
                onClick?.onClick(item, it)
            }
        }

        override fun onBind(item: Any) {
            (item as? FriendEntity)?.let {
                view.tvName.text = it.name
                view.tvStatus.text = it.status
            }

        }
    }
}