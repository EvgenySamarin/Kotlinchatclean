package ru.dvc.kotlin_chat_clean.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.dvc.kotlin_chat_clean.domain.accout.*
import ru.dvc.kotlin_chat_clean.domain.type.None
import timber.log.Timber
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register,
    val loginUseCase: Login,
    val getAccountUseCase: GetAccount,
    val logoutUseCase: Logout,
    val editAccountUseCase: EditAccount
) : BaseViewModel() {

    //данные о регистрации
    var registerData: MutableLiveData<None> = MutableLiveData()
    var accountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var editAccountData: MutableLiveData<AccountEntity> = MutableLiveData()
    var logoutData: MutableLiveData<None> = MutableLiveData()


    /** делегирует выполнение регистрации  */
    fun register(email: String, name: String, password: String) {
        Timber.d("register")

        registerUseCase(Register.Params(email, name, password)) {
            it.either(
                ::handleFailure,
                ::handleRegister
            )
        }
    }

    fun login(email: String, password: String) {
        Timber.d("login")

        loginUseCase(Login.Params(email, password)) {
            it.either(::handleFailure, ::handleAccount)
        }
    }

    fun getAccount() {
        Timber.d("getAccount")

        getAccountUseCase(None()) { it.either(::handleFailure, ::handleAccount) }
    }

    fun logout() {
        Timber.d("logout")

        logoutUseCase(None()) { it.either(::handleFailure, ::handleLogout) }
    }

    fun editAccount(entity: AccountEntity) {
        editAccountUseCase(entity) { it.either(::handleFailure, ::handleEditAccount) }
    }

    private fun handleAccount(account: AccountEntity) {
        Timber.d("handleAccount")

        this.accountData.value = account
    }

    private fun handleEditAccount(account: AccountEntity) {
        this.editAccountData.value = account
    }

    private fun handleLogout(none: None) {
        Timber.d("handleLogout")

        this.logoutData.value = none
    }

    private fun handleRegister(none: None) {
        Timber.d("handleRegister")

        this.registerData.value = none
    }

    override fun onCleared() {
        Timber.d("onCleared")

        super.onCleared()
        registerUseCase.unsubscribe()
        loginUseCase.unsubscribe()
        getAccountUseCase.unsubscribe()
        logoutUseCase.unsubscribe()
    }
}