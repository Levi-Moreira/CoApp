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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        setUpViews()
    }

    fun setUpViews() {

        buttonPlans.onClick {
            Toast.makeText(this, R.string.meet_ou_plans, Toast.LENGTH_SHORT).show()
        }

        buttonEmergencySchedule.onClick {
            Toast.makeText(this, R.string.emergency_sheducling, Toast.LENGTH_SHORT).show()
        }

        buttonContact.onClick {
            Toast.makeText(this, R.string.contact_us, Toast.LENGTH_SHORT).show()
        }

        buttonJoin.onClick {
            Toast.makeText(this, R.string.sign_up, Toast.LENGTH_SHORT).show()
        }

        buttonSignIn.onClick {
            Toast.makeText(this, R.string.sign_in, Toast.LENGTH_SHORT).show()
        }
    }

}
