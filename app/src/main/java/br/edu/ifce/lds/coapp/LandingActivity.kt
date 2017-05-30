package br.edu.ifce.lds.coapp

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_landing.*
import org.jetbrains.anko.onClick

class LandingActivity : BaseActivity() {


    var landingFragment = LandingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)


        landingFragment.arguments = intent.extras
        supportFragmentManager.beginTransaction()
                .add(fragmentHolder.id, landingFragment).commit()

        setUpViews()
    }

    fun setUpViews() {


        buttonSignIn.onClick {
            Toast.makeText(this, R.string.sign_in, Toast.LENGTH_SHORT).show()
        }
    }

}
