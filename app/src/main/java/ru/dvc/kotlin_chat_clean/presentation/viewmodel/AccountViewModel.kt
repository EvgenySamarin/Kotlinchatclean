package ru.dvc.kotlin_chat_clean.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.dvc.kotlin_chat_clean.domain.accout.*
import ru.dvc.kotlin_chat_clean.domain.type.None
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login,
    val getAccountUseCase: GetAccount,
    val logoutUseCase: Logout
) : BaseViewModel () {

    //данные о регистрации
    var registerData: MutableLiveData<None> = MutableLiveData()
    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData: MutableLiveData<None> = MutableLiveData()


/** делегирует выполнение регистрации  */
    fun register(email: String, name: String, password: String) {
        registerUseCase(Register.Params(email, name, password)) { it.either(::handleFailure, ::handleRegister) }
    }

    fun login(email: String, password: String) {
        loginUseCase(Login.Params(email, password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun getAccount() {
        getAccountUseCase(None()) { it.either(::handleFailure, ::handleAccount) }
    }

    fun logout() {
        logoutUseCase(None()) { it.either(::handleFailure, ::handleLogout) }
    }

    private fun handleAccount(account: AccountEntity) {
        this.accountData.value = account
    }

    private fun handleLogout(none: None) {
        this.logoutData.value = none
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
        getAccountUseCase.unsubscribe()
        logoutUseCase.unsubscribe()
    }
}