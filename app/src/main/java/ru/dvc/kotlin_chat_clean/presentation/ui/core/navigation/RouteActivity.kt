package ru.dvc.kotlin_chat_clean.presentation.ui.core.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dvc.kotlin_chat_clean.presentation.ui.App
import timber.log.Timber
import javax.inject.Inject

/** Стартовая activity
 * Для менеджмента запуска activity при открытии приложения.*/
class RouteActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")

        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)

        navigator.showMain(this)
    }
}
