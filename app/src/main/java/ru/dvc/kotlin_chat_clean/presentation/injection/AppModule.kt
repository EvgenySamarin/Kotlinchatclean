package ru.dvc.kotlin_chat_clean.presentation.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dvc.kotlin_chat_clean.data.account.AccountCache
import ru.dvc.kotlin_chat_clean.data.account.AccountRemote
import ru.dvc.kotlin_chat_clean.data.account.AccountRepositoryImpl
import ru.dvc.kotlin_chat_clean.data.friends.FriendsRemote
import ru.dvc.kotlin_chat_clean.data.friends.FriendsRepositoryImpl
import ru.dvc.kotlin_chat_clean.data.media.MediaRepositoryImpl
import ru.dvc.kotlin_chat_clean.domain.accout.AccountRepository
import ru.dvc.kotlin_chat_clean.domain.friends.FriendsRepository
import ru.dvc.kotlin_chat_clean.domain.media.MediaRepository
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

    @Provides
    @Singleton
    fun provideFriendsRepository(remote: FriendsRemote, accountCache: AccountCache): FriendsRepository {
        return FriendsRepositoryImpl(accountCache, remote)
    }

    @Provides
    @Singleton
    fun provideMediaRepository(context: Context): MediaRepository {
        return MediaRepositoryImpl(context)
    }
}