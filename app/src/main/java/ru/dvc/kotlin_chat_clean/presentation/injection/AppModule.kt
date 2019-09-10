package ru.dvc.kotlin_chat_clean.presentation.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dvc.kotlin_chat_clean.data.account.AccountCache
import ru.dvc.kotlin_chat_clean.data.account.AccountRemote
import ru.dvc.kotlin_chat_clean.data.account.AccountRepositoryImpl
import ru.dvc.kotlin_chat_clean.domain.accout.AccountRepository
import timber.log.Timber
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        Timber.d("provideAppContext")

        return context
    }

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache): AccountRepository {
        Timber.d("provideAccountRepository")

        return AccountRepositoryImpl(remote, cache)
    }
}