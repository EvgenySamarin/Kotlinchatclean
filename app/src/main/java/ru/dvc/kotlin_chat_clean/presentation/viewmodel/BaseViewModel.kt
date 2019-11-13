package ru.dvc.kotlin_chat_clean.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.domain.type.HandleOnce

abstract class BaseViewModel : ViewModel(){
    var failureData: MutableLiveData<HandleOnce<Failure>> = MutableLiveData()
    var progressData: MutableLiveData<Boolean> = MutableLiveData()

    /** сеттер для обертывания failure в HandleOnce */
    protected fun handleFailure(failure: Failure){
        this.failureData.value = HandleOnce(failure)
        updateProgress(false)
    }

    /** 2019.11.13 v1 хранит состояние загрузки данных */
    protected fun updateProgress(progress: Boolean) {
        this.progressData.value = progress
    }
}