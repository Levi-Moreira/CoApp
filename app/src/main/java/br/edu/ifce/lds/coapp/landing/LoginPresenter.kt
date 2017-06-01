package br.edu.ifce.lds.coapp.landing

import android.content.Context
import com.google.firebase.auth.AuthCredential
import com.gral.android.actsports.common.BasePresenter

/**
 * Presenter responsible for interepresenting requests from the login fragment
 */

class LoginPresenter(val mView: LoginView, val context: Context) : BasePresenter<LoginView> {


    val mDataHandler: LoginDataHandler = LoginDataHandler(this, context)


    /**
     * Activates authentication when fragment starts
     */
    fun onActivityStart() {
        mDataHandler.turnOnAuthenticationListener()

    }

    /**
     * Disables authentication listener when fragment ends
     */
    fun onActivityStop() {
        mDataHandler.turnOffOnAuthenticationListener()
    }


    /**
     * Authenticates an user using email and password
     */
    fun authenticateWithPassword(username: String, password: String) {

        mView.showLoading()
        mDataHandler.authenticateWithPassword(username, password)

    }

    /**
     * Shows error message if something goes wrong
     */
    fun authenticationFailed() {
        mView.showAuthenticationFailed()
    }

    /**
     * Shows successful message if everything goes well
     */
    fun authenticationSuceeded() {
        mView.showAuthenticationSucceed()
    }

    /**
     * Use facebook credentials to authenticate a user
     */
    fun  authenticateWithFacebookCredentials(credential: AuthCredential?) {
        mDataHandler.authenticateWithFacebook(credential)
    }


}