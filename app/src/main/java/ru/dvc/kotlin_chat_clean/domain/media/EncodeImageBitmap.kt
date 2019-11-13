package ru.dvc.kotlin_chat_clean.domain.media

import android.graphics.Bitmap
import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import javax.inject.Inject

/**
 * Use case, для кодирования изображения в строку
 *
 * @author EvgenySamarin [GitHub](https://github.com/EvgenySamarin)
 * @since 2019.11.13 v1
 */
class EncodeImageBitmap @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<String, Bitmap?>() {

    override suspend fun run(params: Bitmap?) = mediaRepository.encodeImageBitmap(params)
}