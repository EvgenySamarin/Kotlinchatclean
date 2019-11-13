package ru.dvc.kotlin_chat_clean.presentation.ui.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import ru.dvc.kotlin_chat_clean.data.remote.service.ServiceFactory

/**
 * класс помощник для загрузки изображений
 *
 * @author EvgenySamarin [GitHub](https://github.com/EvgenySamarin)
 * @since 2019.11.14 v1
 */
object GlideHelper {
    fun loadImage(context: Context, path: String?, iv: ImageView, placeholder: Drawable = iv.drawable) {
        val imgPath = ServiceFactory.SERVER_URL + path?.replace("..", "")

        Glide.with(context)
            .load(imgPath)
            .placeholder(placeholder)
            .error(placeholder)
            .into(iv)
    }

    fun loadImage(context: Context, path: String?, iv: ImageView, placeholder: Int) {
        loadImage(context, path, iv, context.resources.getDrawable(placeholder))
    }
}
