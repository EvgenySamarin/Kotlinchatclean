package ru.dvc.kotlin_chat_clean.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dvc.kotlin_chat_clean.domain.type.HandleOnce
import ru.dvc.kotlin_chat_clean.domain.type.Failure

abstract class BaseViewModel : ViewModel(){
    var failureData: MutableLiveData<HandleOnce<Failure>> = MutableLiveData()

    /** сеттер для обертывания failure в HandleOnce */
    protected fun handleFailure(failure: Failure){
        this.failureData.value = HandleOnce(failure)
    }
}