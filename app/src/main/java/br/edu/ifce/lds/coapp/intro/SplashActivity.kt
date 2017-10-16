package br.edu.ifce.lds.coapp.intro

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.application.UserSession
import br.edu.ifce.lds.coapp.landing.views.LandingActivity
import br.edu.ifce.lds.coapp.login.LoginActivity
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.startActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SplashActivity : AppCompatActivity() {


    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        discoveryKeyHarsh()
        signInFirebaseAnonimous()
        startActivity<LoginActivity>()
    }

    /**
     * Use it when needs to discovery the key harsh
     */
    fun discoveryKeyHarsh() {
        try {
            val info = packageManager.getPackageInfo(
                    "com.hmr.android.taskr",
                    PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {

        } catch (e: NoSuchAlgorithmException) {

        }

    }

    fun signInFirebaseAnonimous() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser?.getToken(true)
                                ?.addOnCompleteListener({
                                    task ->
                                    if (task.isSuccessful) {
                                        val session = UserSession(PreferencesUtil(this))
                                        session.setToken(task.result.token!!)
                                        Log.d("SIGNINTOKEN", task.result.token!!)
                                    }

                                })


                    }else{
                        Log.d("SIGNINTOKEN", task.result.toString())
                    }
                })
    }
}
