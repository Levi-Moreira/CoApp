package br.edu.ifce.lds.coapp.landing

import android.app.Activity
import android.content.Context
import android.util.Log
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.gral.android.actsports.application.UserSession

/**
 * Lower level communication class responsible for dealing with the database (Firebase)
 */

class LoginDataHandler(val mPresenter: LoginPresenter, val context: Context) {

    var mAuth: FirebaseAuth
    var mAuthListener: FirebaseAuth.AuthStateListener
    var mPrefs: PreferencesUtil

    val TAG = "LoginDataHandler"

    init {
        mPrefs = PreferencesUtil(context)

        mAuth = FirebaseAuth.getInstance()

        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.uid)
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }

        }
    }

    fun turnOnAuthenticationListener() {
        mAuth.addAuthStateListener(mAuthListener)
    }

    fun turnOffOnAuthenticationListener() {
        mAuth.removeAuthStateListener(mAuthListener)
    }

    fun authenticateWithPassword(username: String, password: String) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(context as Activity) { task ->

                    if (!task.isSuccessful) {
                        Log.w(TAG, "signInWithEmail:failed", task.exception)
                        mPresenter.authenticationFailed()
                    } else {
                        val user = mAuth.currentUser

                        if (user != null) {
                            val userF = User("", username, user.uid)

                            val session = UserSession(mPrefs)
                            session.signin(userF)

                            mPresenter.authenticationSuceeded()

                        }
                    }
                }
    }

    fun authenticateWithFacebook(credential: AuthCredential?) {
        if (credential != null) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(context as Activity) {
                        task ->
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful)
                        if (!task.isSuccessful) {
                            mPresenter.authenticationFailed()
                        } else {
                            val user = mAuth.currentUser


                            if (user != null) {

                                val userName: String = user.displayName?.let { it } ?: ""
                                val userEmail: String = user.email?.let { it } ?: ""

                                val userF = User(userName, userEmail, "", user.uid)

                                val session = UserSession(mPrefs)
                                session.signin(userF)
                                mPresenter.authenticationSuceeded()
                            }
                        }

                    }
        } else {
            mPresenter.authenticationFailed()
        }

    }


}
