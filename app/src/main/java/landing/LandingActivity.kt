package landing

import android.os.Bundle
import android.widget.Toast
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
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
