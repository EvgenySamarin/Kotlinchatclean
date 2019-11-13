package ru.dvc.kotlin_chat_clean.domain.media

import android.graphics.Bitmap
import android.net.Uri
import ru.dvc.kotlin_chat_clean.domain.type.Either
import ru.dvc.kotlin_chat_clean.domain.type.Failure

/**
 * Интерфейс репозитория, для работы с файлами
 */
interface MediaRepository {
    /** 2019.11.12 v1 создать файл пустышку */
    fun createImageFile(): Either<Failure, Uri>
    /** 2019.11.12 v1 кодирует изображение в строку для последующей передачи на сервер */
    fun encodeImageBitmap(bitmap: Bitmap?): Either<Failure, String>
    /** 2019.11.12 v1 возвращает изображение по указанному пути */
    fun getPickedImage(uri: Uri?): Either<Failure, Bitmap>
}