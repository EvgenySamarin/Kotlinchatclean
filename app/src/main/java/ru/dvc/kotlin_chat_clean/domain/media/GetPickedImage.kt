package ru.dvc.kotlin_chat_clean.domain.media

import android.graphics.Bitmap
import android.net.Uri
import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import javax.inject.Inject

/**
 * UseCase для получения изображения по Uri
 *
 * @author EvgenySamarin [GitHub](https://github.com/EvgenySamarin)
 * @since 2019.11.13 v1
 */
class GetPickedImage @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<Bitmap, Uri?>() {

    override suspend fun run(params: Uri?) = mediaRepository.getPickedImage(params)
}