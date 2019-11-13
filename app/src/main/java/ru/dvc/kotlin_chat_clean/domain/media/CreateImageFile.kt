package ru.dvc.kotlin_chat_clean.domain.media

import android.net.Uri
import ru.dvc.kotlin_chat_clean.domain.iteractor.UseCase
import ru.dvc.kotlin_chat_clean.domain.type.None
import javax.inject.Inject

/**
 * Для создания изображения
 * @param mediaRepository - доступ к данным устройства
 *
 * @author EvgenySamarin [GitHub](https://github.com/EvgenySamarin)
 * @since 2019.11.13 v1
 */
class CreateImageFile @Inject constructor(
    private val mediaRepository: MediaRepository
) : UseCase<Uri, None>() {

    override suspend fun run(params: None) = mediaRepository.createImageFile()
}