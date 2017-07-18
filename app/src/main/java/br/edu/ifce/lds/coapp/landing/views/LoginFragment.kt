package br.edu.ifce.lds.coapp.landing.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.edu.ifce.lds.coapp.MainMenuActivity
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.R.layout.fragment_login
import br.edu.ifce.lds.coapp.landing.presenters.LoginPresenter
import br.edu.ifce.lds.coapp.utils.isValid
import br.edu.ifce.lds.coapp.utils.isValidEmail
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.startActivity


/**
 * Login fragment to be loaded in the main landing screen
 */
class LoginFragment() : Fragment(), LoginView {


    //connects to activity for special requests
    lateinit var mCallback: ILoginFragmentCallbacks

    //presenterImpl for this view
    lateinit var mPresenter: LoginPresenter


    //manages the facebook callback
    lateinit var mCallbackManager: CallbackManager

    //easier acess for the context
    lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //start up facebook sdk before creating views
        FacebookSdk.sdkInitialize(this.activity.applicationContext)

        //capture context for later use
        mContext = this.context

        //start up callback for facebook login
        mCallbackManager = CallbackManager.Factory.create()

        //inflate view and return
        return inflater!!.inflate(fragment_login, container, false)
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //start up the presenterImpl
        mPresenter = LoginPresenter(this, this.context)

        //configure facebook login button
        setUpFacebook()

        //configure other buttons and views
        backButton.onClick {
            //ask activity to switch fragments
            mCallback.onClickBackButton()
        }

        signIn.onClick {
            //verifies if fields are correctly filled
            if (edtEmail.isValidEmail && edtPassword.isValid) {
                //if so send info to Firebase
                mPresenter.authenticateWithPassword(edtEmail.text.toString(), edtPassword.text.toString())
            } else {

                //if not identify who`s not correctly filled and warn user
                if (!edtEmail.isValidEmail) {
                    edtEmail.error = "Preeencha o seu email."
                    edtEmail.invalidate()
                }

                if (!edtPassword.isValid) {
                    edtPassword.error = "Preeencha a sua senha."
                    edtPassword.invalidate()
                }
            }
        }
    }

    /**
     * Sets up facebook login button configuration
     */
    private fun setUpFacebook() {

        //tell what to bring from the login
        facebookSignIn.setReadPermissions("email", "public_profile")

        //register the callback results
        facebookSignIn.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

                handleFacebookAccessToken(loginResult.accessToken)
                facebookSignIn.text = getString(R.string.facebook_sign_in)

            }

            override fun onCancel() {
                facebookSignIn.text = getString(R.string.facebook_sign_in)
            }

            override fun onError(error: FacebookException) {
                facebookSignIn.text = getString(R.string.facebook_sign_in)
                Toast.makeText(mContext, R.string.connection_failed,
                        Toast.LENGTH_SHORT).show()
            }
        })
    }

    /**
     * Send credentials to presenterImpl for registering in firebase
     * @param token the acess token provided by Facebook
     */
    fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        mPresenter.authenticateWithFacebookCredentials(credential)

    }


    //when fragment is attached, associates with the callback interface
    override fun onAttach(context: android.content.Context?) {
        super.onAttach(context)
        try {
            //attache the callback for acessing activity
            mCallback = context as ILoginFragmentCallbacks

        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement ILoginFragmentCallbacks");
        }
    }


    /**
     * When facebook activity returns this function should be called from the activity
     */
    fun onActivtyReturned(requestCode: Int, resultCode: Int, data: Intent?){
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
    }

    /**
     * In case an authentication problem happened
     */
    override fun showAuthenticationFailed() {
        Toast.makeText(this.context, R.string.auth_failed,
                Toast.LENGTH_SHORT).show()
    }



    override fun showLoading() {
        mCallback.showLoading()
    }

    override fun hideLoading() {
        mCallback.hideLoading()
    }

    override fun showAuthenticationSucceed() {
        mCallback.finishActivity()
    }


    override fun onStart() {
        super.onStart()
        mPresenter.onActivityStart()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onActivityStop()
    }

    /**
     * Intefaces with the login fragment and the landing activity
     */
    interface ILoginFragmentCallbacks {
        fun onClickBackButton()

        fun finishActivity()

        fun showLoading()

        fun hideLoading()
    }


}// Required empty public constructor
