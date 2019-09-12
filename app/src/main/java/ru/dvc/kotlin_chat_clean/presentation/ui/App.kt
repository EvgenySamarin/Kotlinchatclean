package ru.dvc.kotlin_chat_clean.presentation.ui

import android.app.Application
import dagger.Component
import ru.dvc.kotlin_chat_clean.BuildConfig
import ru.dvc.kotlin_chat_clean.presentation.injection.AppModule
import ru.dvc.kotlin_chat_clean.presentation.injection.CacheModule
import ru.dvc.kotlin_chat_clean.presentation.injection.RemoteModule
import ru.dvc.kotlin_chat_clean.presentation.injection.ViewModelModule
import ru.dvc.kotlin_chat_clean.presentation.ui.core.logging.ReleaseTree
import ru.dvc.kotlin_chat_clean.presentation.ui.core.navigation.RouteActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.register.RegisterActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.register.RegisterFragment
import ru.dvc.kotlin_chat_clean.presentation.ui.firebase.FirebaseService
import ru.dvc.kotlin_chat_clean.presentation.ui.home.ChatsFragment
import ru.dvc.kotlin_chat_clean.presentation.ui.home.HomeActivity
import ru.dvc.kotlin_chat_clean.presentation.ui.login.LoginFragment
import timber.log.Timber
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(ReleaseTree(applicationContext)) //отключаем логи в релизе

        initAppComponent()

        Timber.d("onCreate")
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {
    //trees
    fun inject(releaseTree: ReleaseTree)

    //activities
    fun inject(activity: RegisterActivity)
    fun inject(activity: RouteActivity)
    fun inject(activity: HomeActivity)


    //fragments
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ChatsFragment)


    //services
    fun inject(service: FirebaseService)
}