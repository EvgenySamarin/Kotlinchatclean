package ru.dvc.kotlin_chat_clean.presentation.injection

import dagger.Module
import dagger.Provides
import ru.dvc.kotlin_chat_clean.BuildConfig
import ru.dvc.kotlin_chat_clean.data.account.AccountRemote
import ru.dvc.kotlin_chat_clean.data.friends.FriendsRemote
import ru.dvc.kotlin_chat_clean.data.remote.account.AccountRemoteImpl
import ru.dvc.kotlin_chat_clean.data.remote.core.Request
import ru.dvc.kotlin_chat_clean.data.remote.friends.FriendsRemoteImpl
import ru.dvc.kotlin_chat_clean.data.remote.service.ApiService
import ru.dvc.kotlin_chat_clean.data.remote.service.ServiceFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        Timber.d("provideApiService")

        return ServiceFactory.makeService(BuildConfig.DEBUG)
    }

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, apiService: ApiService): AccountRemote {
        Timber.d("provideAccountRemote")

        return AccountRemoteImpl(request, apiService)
    }

    @Singleton
    @Provides
    fun provideFriendsRemote(request: Request, apiService: ApiService): FriendsRemote {
        return FriendsRemoteImpl(request, apiService)
    }
}