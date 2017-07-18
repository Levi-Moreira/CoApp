package br.edu.ifce.lds.coapp.common

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_landing.*
import org.jetbrains.anko.onClick
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Just for applying the custom font to all activities
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
