package br.edu.ifce.lds.coapp.login.dhandlers

import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.apiservice.LoginRequest
import br.edu.ifce.lds.coapp.apiservice.LoginResponse
import br.edu.ifce.lds.coapp.login.entities.User
import br.edu.ifce.lds.coapp.login.mvp.LoginContract
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

/**
 * Lower level communication class responsible for dealing with the database (Firebase)
 */

class LoginDataHandler @Inject constructor() : LoginContract.LoginDataHandler {
    val TAG = "LoginDataHandler"

    @Inject
    lateinit var backend: CoAppBackend


    @Inject
    lateinit var mPrefs: PreferencesUtil

    override fun signIn(username: String, password: String): Observable<Response<LoginResponse>> {
        var user = User(username = username, password = password)

        return backend.backendAPI.signIn(LoginRequest(user), backend.publicTokenFormatted)

    }
}
