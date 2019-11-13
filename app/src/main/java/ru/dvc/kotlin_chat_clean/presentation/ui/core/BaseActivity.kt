package ru.dvc.kotlin_chat_clean.presentation.ui.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.toolbar.*
import ru.dvc.kotlin_chat_clean.R
import ru.dvc.kotlin_chat_clean.domain.type.Failure
import ru.dvc.kotlin_chat_clean.presentation.ui.core.navigation.Navigator
import timber.log.Timber
import javax.inject.Inject


/**
 *
 *
 * @author EY.Samarin
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract var fragment: BaseFragment

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var permissionManager: PermissionManager

    open val contentId = R.layout.activity_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentId)

        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        fragment.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(
        savedInstanceState: Bundle? = null,
        fragment: BaseFragment = this.fragment
    ) {
        Timber.d("addFragment")

        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment)
        }
    }

    fun replaceFragment(fragment: BaseFragment) {
        this.fragment = fragment
        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer, fragment)
        }
    }


    fun showProgress() {
        Timber.d("showProgress")
        progressStatus(View.VISIBLE)
    }

    fun hideProgress() {
        Timber.d("hideProgress")
        progressStatus(View.GONE)
    }

    fun progressStatus(viewStatus: Int) {
        Timber.d("progressStatus")
        toolbar_progress_bar.visibility = viewStatus
    }


    fun hideSoftKeyboard() {
        Timber.d("hideSoftKeyboard")

        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    //BACKLOG [20190912] смотри за этой штукой внимательно зачем-то функцию сделали open
    open fun handleFailure(failure: Failure?) {
        Timber.d("handleFailure")

        hideProgress()

        when (failure) {
            is Failure.NetworkConnectionError -> showMessage(getString(R.string.error_network))
            is Failure.ServerError -> showMessage(getString(R.string.error_server))
            is Failure.EmailAlreadyExistError -> showMessage(getString(R.string.error_email_already_exist))
            is Failure.AuthError -> showMessage(getString(R.string.error_auth))
            is Failure.TokenError -> navigator.showLogin(this)
            is Failure.AlreadyFriendError -> showMessage(getString(R.string.error_already_friend))
            is Failure.AlreadyRequestedFriendError -> showMessage(getString(R.string.error_already_requested_friend))
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        Timber.d("get viewModel")
        val vm = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
        vm.body()
        return vm
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionManager.requestObject?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun Activity?.base(block: BaseActivity.() -> Unit) {
    Timber.d("call .base")
    (this as? BaseActivity)?.let(block)
}