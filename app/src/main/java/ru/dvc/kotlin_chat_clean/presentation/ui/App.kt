package ru.dvc.kotlin_chat_clean.presentation.ui

import android.app.Application
import dagger.Component
import ru.dvc.kotlin_chat_clean.presentation.injection.AppModule
import ru.dvc.kotlin_chat_clean.presentation.injection.CacheModule
import ru.dvc.kotlin_chat_clean.presentation.injection.RemoteModule
import ru.dvc.kotlin_chat_clean.presentation.injection.ViewModelModule
import ru.dvc.kotlin_chat_clean.presentation.ui.core.navigation.RouteActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.register.RegisterActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.register.RegisterFragment
import ru.dvc.kotlin_chat_clean.presentation.ui.firebase.FirebaseService
import ru.dvc.kotlin_chat_clean.presentation.ui.home.ChatsFragment
import ru.dvc.kotlin_chat_clean.presentation.ui.home.HomeActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.login.LoginFragment
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {
    //activities
    fun inject(activity: RegisterActivity)

    //fragments
    fun inject(fragment: RegisterFragment)

    //services
    fun inject(service: FirebaseService)

    fun inject(activity: RouteActivity)
    fun inject(activity: HomeActivity)

    fun inject(fragment: LoginFragment)
    fun inject(fragment: ChatsFragment)

}