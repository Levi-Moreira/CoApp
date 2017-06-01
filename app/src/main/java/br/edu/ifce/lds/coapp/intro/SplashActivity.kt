package br.edu.ifce.lds.coapp.intro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifce.lds.coapp.landing.LandingActivity
import br.edu.ifce.lds.coapp.R
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        startActivity<LandingActivity>()
    }
}
