package ru.dvc.kotlin_chat_clean.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.dvc.kotlin_chat_clean.data.account.AccountCache
import ru.dvc.kotlin_chat_clean.data.cache.AccountCacheImpl
import ru.dvc.kotlin_chat_clean.data.cache.ChatDatabase
import ru.dvc.kotlin_chat_clean.data.cache.SharedPrefsManager
import ru.dvc.kotlin_chat_clean.data.friends.FriendsCache
import javax.inject.Singleton

@Module
class CacheModule {

    //в Шаред pref будем хранить токен, флаг авторизации и некоторые параметры настроек
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAccountCache(prefsManager: SharedPrefsManager): AccountCache {
        return AccountCacheImpl(prefsManager)
    }

    @Provides
    @Singleton
    fun provideChatDatabase(context: Context): ChatDatabase {
        return ChatDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFriendsCache(chatDatabase: ChatDatabase): FriendsCache {
        return chatDatabase.friendsDao
    }
}