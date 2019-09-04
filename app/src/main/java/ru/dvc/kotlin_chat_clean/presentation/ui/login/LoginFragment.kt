package ru.dvc.kotlin_chat_clean.presentation.ui.login

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.etEmail
import kotlinx.android.synthetic.main.fragment_register.etPassword
import ru.dvc.kotlin_chat_clean.R
import ru.dvc.kotlin_chat_clean.domain.accout.AccountEntity
import ru.dvc.kotlin_chat_clean.presentation.ui.App
import ru.dvc.kotlin_chat_clean.presentation.ui.core.BaseFragment
import ru.dvc.kotlin_chat_clean.presentation.ui.core.ext.onFailure
import ru.dvc.kotlin_chat_clean.presentation.ui.core.ext.onSuccess
import ru.dvc.kotlin_chat_clean.presentation.viewmodel.AccountViewModel

class LoginFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_login
    override val titleToolbar = R.string.screen_login

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)


        accountViewModel = viewModel {
            onSuccess(accountData, ::renderAccount)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {
            validateFields()
        }

        btnRegister.setOnClickListener {
            activity?.let { navigator.showSignUp(it) }
        }
    }

    private fun validateFields() {
        hideSoftKeyboard()
        val allFields = arrayOf(etEmail, etPassword)
        var allValid = true
        for (field in allFields) {
            //allValid = field.testValidity() && allValid
        }
        if (allValid) {
            login(etEmail.text.toString(), etPassword.text.toString())
        }
    }

    private fun login(email: String, password: String) {
        showProgress()
        accountViewModel.login(email, password)
    }

    private fun renderAccount(account: AccountEntity?) {
        hideProgress()
        activity?.let {
            navigator.showHome(it)
            it.finish()
        }
    }
}