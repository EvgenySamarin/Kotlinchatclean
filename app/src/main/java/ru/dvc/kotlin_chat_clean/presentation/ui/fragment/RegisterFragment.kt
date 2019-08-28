package ru.dvc.kotlin_chat_clean.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_register.*
import ru.dvc.kotlin_chat_clean.R
import ru.dvc.kotlin_chat_clean.domain.type.None
import ru.dvc.kotlin_chat_clean.presentation.ui.App
import ru.dvc.kotlin_chat_clean.presentation.ui.ext.onFailure
import ru.dvc.kotlin_chat_clean.presentation.ui.ext.onSuccess
import ru.dvc.kotlin_chat_clean.presentation.viewmodel.AccountViewModel

class RegisterFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_register
    override val titleToolbar = R.string.register

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(registerData, ::handleRegister)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNewMembership.setOnClickListener {
            register()
        }
    }

    private fun validateFields(): Boolean {
        val allFields = arrayOf(etEmail, etPassword, etConfirmPassword, etusername)
        var allValid = true
        for (field in allFields) {
            //allValid = field.testValidity() && allValid для этого требуется сторонняя библиотека
        }
        return allValid && validatePasswords()
    }

    private fun validatePasswords(): Boolean {
        val valid = etPassword.text.toString() == etConfirmPassword.text.toString()
        if (!valid) {
            showMessage(getString(R.string.error_password_mismatch))
        }
        return valid
    }

    private fun register() {
        hideSoftKeyboard()

        val allValid = validateFields()

        if (allValid) {
            showProgress()

            accountViewModel.register(
                etEmail.text.toString(),
                etusername.text.toString(),
                etPassword.text.toString()
            )
        }
    }

    private fun handleRegister(none: None? = None()) {
        hideProgress()
        showMessage("Аккаунт создан")
    }
}