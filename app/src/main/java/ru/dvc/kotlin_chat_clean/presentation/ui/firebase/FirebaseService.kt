package ru.dvc.kotlin_chat_clean.presentation.ui.firebase

import android.os.Handler
import android.os.Looper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.dvc.kotlin_chat_clean.domain.accout.UpdateToken
import ru.dvc.kotlin_chat_clean.presentation.ui.App
import timber.log.Timber
import javax.inject.Inject

class FirebaseService : FirebaseMessagingService() {

    @Inject
    lateinit var updateToken: UpdateToken

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onCreate() {
        Timber.d("onCreate")

        super.onCreate()
        App.appComponent.inject(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("onMessageReceived")

        Handler(Looper.getMainLooper()).post {
            notificationHelper.sendNotification(remoteMessage)

        }
    }

    override fun onNewToken(token: String) {
        Timber.d("onNewToken: $token")

        if (token != null) {
            updateToken(UpdateToken.Params(token))
        }
    }
}