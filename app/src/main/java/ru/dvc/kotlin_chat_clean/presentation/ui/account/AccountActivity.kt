package ru.dvc.kotlin_chat_clean.presentation.ui.account

import android.os.Bundle
import ru.dvc.kotlin_chat_clean.presentation.ui.App
import ru.dvc.kotlin_chat_clean.presentation.ui.core.BaseActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.core.BaseFragment

class AccountActivity : BaseActivity() {
    override var fragment: BaseFragment = AccountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}
