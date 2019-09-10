package ru.dvc.kotlin_chat_clean.presentation.ui.core.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.dvc.kotlin_chat_clean.domain.type.HandleOnce
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import timber.log.Timber

/** */
fun <T : Any, L : LiveData<T>> LifecycleOwner.onSuccess(liveData: L, body: (T?) -> Unit) {
    Timber.d("onSuccess for $liveData")

    liveData.observe(this, Observer(body))
}

/** */
fun <L : LiveData<HandleOnce<Failure>>> LifecycleOwner.onFailure(
    liveData: L,
    body: (Failure?) -> Unit
) {
    Timber.d("onFailure for $liveData")

    liveData.observe(this, Observer {
        it.getContentIfNotHandled()?.let(body)
    })
}