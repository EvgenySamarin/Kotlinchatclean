package ru.dvc.kotlin_chat_clean.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.dvc.kotlin_chat_clean.domain.accout.Register
import ru.dvc.kotlin_chat_clean.domain.type.None
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register
) : BaseViewModel () {

    //данные о регистрации
    var registerData: MutableLiveData<None> = MutableLiveData()


/** делегирует выполнение регистрации  */
    fun register(email: String, name: String, password: String) {
        registerUseCase(Register.Params(email, name, password)) { it.either(::handleFailure, ::handleRegister) }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
    }
}