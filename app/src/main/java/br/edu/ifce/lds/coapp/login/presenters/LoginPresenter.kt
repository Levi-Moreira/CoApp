package br.edu.ifce.lds.coapp.login.presenters

import br.edu.ifce.lds.coapp.login.dhandlers.LoginDataHandler
import br.edu.ifce.lds.coapp.login.mvp.LoginContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * Presenter responsible for interepresenting requests from the login fragment
 */

class LoginPresenter @Inject constructor(val mView: LoginContract.LoginView) : LoginContract.LoginPresenter {


    @Inject
    lateinit var mDataHandler: LoginDataHandler

    override fun signUserIn(email: String, password: String) {
        mDataHandler.signIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            mView.hideLoading()
                            if (response.isSuccessful) {
                                mView.signInSuccess()
                            } else {
                                mView.signInError()
                            }
                        },
                        { error ->
                            error.printStackTrace()
                            mView.hideLoading()
                            if (error is TimeoutException) {
                                mView.connetionError()
                            }
                        }
                )
    }
}