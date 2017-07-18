package br.edu.ifce.lds.coapp.landing.views

import android.content.Intent
import android.support.v4.app.FragmentTransaction
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import br.edu.ifce.lds.coapp.MainMenuActivity
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.R.anim.*
import br.edu.ifce.lds.coapp.R.layout.activity_landing
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.di.ContactActivity
import br.edu.ifce.lds.coapp.plans.views.PlanListActivity
import kotlinx.android.synthetic.main.activity_landing.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity


/**
 * Holds the landing page and login page fragments
 */
class LandingActivity : BaseActivity(), LoginFragment.ILoginFragmentCallbacks, LandingFragment.ILandingCallbacks {


    //start up the gragments
    var landingFragment = LandingFragment()
    var loginFragment = LoginFragment()


    //helps with changing between fragments
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_landing)


        //show the first fragment
        landingFragment.arguments = intent.extras
        supportFragmentManager.beginTransaction()
                .add(fragmentHolder.id, landingFragment).commit()

        //configure some views
        setUpViews()
    }

    /**
     * Sets up some button clicks and views configurations
     */
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

    /**
     * When one clicks in the back button from the login fragment
     */
    override fun onClickBackButton() {
        //change the fragment to the landing one
        fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(fragment_slide_left_enter, fragment_slide_right_exit)
        fragmentTransaction.replace(fragmentHolder.id, landingFragment)

        //restoes the sign in button
        buttonSignIn.visibility = VISIBLE
        fragmentTransaction.addToBackStack(null)

        //effectively change fragments
        fragmentTransaction.commit()

    }

    /**
     * starts up the main menu activity
     */
    override fun finishActivity() {
        Toast.makeText(this, R.string.login_sucessful, Toast.LENGTH_SHORT).show()
        startActivity<MainMenuActivity>()
        finish()
    }

    override fun hideLoading() {
        progressWheel.visibility = GONE
    }

    override fun showLoading() {
        progressWheel.visibility = VISIBLE
    }

    /**
     * When an intent for outside was made, the result will come to this method
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginFragment.onActivtyReturned(requestCode, resultCode, data)
    }

    /**
     * When the landing fragment asks for a the contact activtiy to be started
     */
    override fun startContactActivity() {
        startActivity<ContactActivity>()
        overridePendingTransition(R.anim.fragment_slide_right_enter, fragment_slide_left_exit)
    }

    override fun startPlansActivity() {
        startActivity<PlanListActivity>()
        overridePendingTransition(R.anim.fragment_slide_right_enter, fragment_slide_left_exit)
    }
}
