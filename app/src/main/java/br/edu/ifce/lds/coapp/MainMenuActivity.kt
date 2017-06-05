package br.edu.ifce.lds.coapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.ContactActivity
import br.edu.ifce.lds.coapp.landing.views.LandingFragment
import org.jetbrains.anko.startActivity
import android.transition.Slide



class MainMenuActivity : BaseActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
    }

}
