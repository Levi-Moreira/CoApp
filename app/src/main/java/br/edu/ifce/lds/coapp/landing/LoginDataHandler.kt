package br.edu.ifce.lds.coapp.landing

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by ellca on 01/06/2017.
 */

class LoginDataHandler(val mPresenter: LoginPresenter, val context: Context) {

    var mAuth: FirebaseAuth
    var mAuthListener: FirebaseAuth.AuthStateListener

    val TAG = "LoginDataHandler"

    init {
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

}
