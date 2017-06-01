package br.edu.ifce.lds.coapp.landing


import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.Toast
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import kotlinx.android.synthetic.main.activity_landing.*
import org.jetbrains.anko.onClick

class LandingActivity : BaseActivity(), LoginFragment.ILoginFragmentCallbacks {



    var landingFragment = LandingFragment()
    var loginFragment = LoginFragment()


    lateinit var fragmentTransaction: FragmentTransaction

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
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_right_enter, R.anim.fragment_slide_left_exit)
            fragmentTransaction.replace(fragmentHolder.id, loginFragment)
            buttonSignIn.visibility = View.GONE
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onClickBackButton() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_right_exit)
        fragmentTransaction.replace(fragmentHolder.id, landingFragment)
        buttonSignIn.visibility = View.VISIBLE
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

}
