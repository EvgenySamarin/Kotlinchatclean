package ru.dvc.kotlin_chat_clean.presentation.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.dvc.kotlin_chat_clean.R
import ru.dvc.kotlin_chat_clean.data.cache.AccountCacheImpl
import ru.dvc.kotlin_chat_clean.data.cache.SharedPrefsManager
import ru.dvc.kotlin_chat_clean.data.account.AccountRepositoryImpl
import ru.dvc.kotlin_chat_clean.domain.accout.AccountRepository
import ru.dvc.kotlin_chat_clean.domain.accout.Register
import ru.dvc.kotlin_chat_clean.data.remote.account.AccountRemoteImpl
import ru.dvc.kotlin_chat_clean.data.remote.core.NetworkHandler
import ru.dvc.kotlin_chat_clean.data.remote.core.Request
import ru.dvc.kotlin_chat_clean.data.remote.service.ServiceFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //создаём shared preference хранилку уровня приложения, доступную только внути приложения
        val sharedPrefs = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)

        val accountCache = AccountCacheImpl(SharedPrefsManager(sharedPrefs))
        val accountRemote = AccountRemoteImpl(
            Request(NetworkHandler(this)),
            ServiceFactory.makeService(true)
        )

        val accountRepository: AccountRepository =
            AccountRepositoryImpl(accountRemote, accountCache)



        accountCache.saveToken("12345")

        val register = Register(accountRepository)
        register(Register.Params("abcd@m.com", "abcd", "123")) {
            it.either({
                Toast.makeText(this, it.javaClass.simpleName, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "Аккаунт создан", Toast.LENGTH_SHORT).show()
            })
        }

    }
}
