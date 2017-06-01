package br.edu.ifce.lds.coapp.landing.views

import br.edu.ifce.lds.coapp.R.anim.*
import br.edu.ifce.lds.coapp.R.layout.activity_landing
import kotlinx.android.synthetic.main.activity_landing.*
import org.jetbrains.anko.onClick


class LandingActivity : br.edu.ifce.lds.coapp.common.BaseActivity(), LoginFragment.ILoginFragmentCallbacks {



    var landingFragment = LandingFragment()
    var loginFragment = LoginFragment()


    lateinit var fragmentTransaction: android.support.v4.app.FragmentTransaction

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_landing)


        landingFragment.arguments = intent.extras
        supportFragmentManager.beginTransaction()
                .add(fragmentHolder.id, landingFragment).commit()

        setUpViews()
    }

    fun setUpViews() {

        buttonSignIn.onClick {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(fragment_slide_right_enter, fragment_slide_left_exit)
            fragmentTransaction.replace(fragmentHolder.id, loginFragment)

            buttonSignIn.visibility = android.view.View.GONE
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onClickBackButton() {
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(fragment_slide_left_enter, fragment_slide_right_exit)
        fragmentTransaction.replace(fragmentHolder.id, landingFragment)

        buttonSignIn.visibility = android.view.View.VISIBLE
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

}
