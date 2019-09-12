package ru.dvc.kotlin_chat_clean.presentation.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.dvc.kotlin_chat_clean.presentation.viewmodel.AccountViewModel
import ru.dvc.kotlin_chat_clean.presentation.viewmodel.FriendsViewModel
import ru.dvc.kotlin_chat_clean.presentation.viewmodel.ViewModelFactory

@Module
abstract class ViewModelModule {
    //Биндит фабрику для создания ViewModel
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FriendsViewModel::class)
    abstract fun bindFriendsViewModel(friendsViewModel: FriendsViewModel): ViewModel
}
