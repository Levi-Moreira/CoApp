package br.edu.ifce.lds.coapp.login.mvp

import br.edu.ifce.lds.coapp.apiservice.LoginResponse
import io.reactivex.Observable
import retrofit2.Response


/**
 * Created by lds on 10/23/17.
 */

interface LoginContract {
    interface LoginView {

        fun showLoading()

        fun hideLoading()
        fun connetionError()
        fun signInSuccess()
        fun signInError()
    }

    interface LoginPresenter {
        fun signUserIn(email: String, password: String)

    }

    interface LoginDataHandler {
        fun signIn(email: String, password: String): Observable<Response<LoginResponse>>
    }
}