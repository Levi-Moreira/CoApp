package br.edu.ifce.lds.coapp.intro

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import br.edu.ifce.lds.coapp.landing.views.LandingActivity
import br.edu.ifce.lds.coapp.R
import org.jetbrains.anko.startActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        discoveryKeyHarsh()
        startActivity<LandingActivity>()
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
}
