package br.edu.ifce.lds.coapp.landing.views

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import br.edu.ifce.lds.coapp.MainMenuActivity
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.R.layout.fragment_login
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.startActivity


/**
 * Login fragment to be loaded in the main landing screen
 */
class LoginFragment() : Fragment(), LoginView {



    lateinit var mCallback: ILoginFragmentCallbacks

    override fun onCreateView(inflater: android.view.LayoutInflater?, container: android.view.ViewGroup?,
                              savedInstanceState: android.os.Bundle?): android.view.View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(fragment_login, container, false)
    }

    override fun onViewCreated(view: android.view.View?, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.onClick {
            mCallback.onClickBackButton()
        }
    }



    override fun onAttach(context: android.content.Context?) {
        super.onAttach(context)
        try {
            mCallback = context as ILoginFragmentCallbacks
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement ILoginFragmentCallbacks");
        }
    }


    override fun showAuthenticationFailed() {
        Toast.makeText(this.context, R.string.auth_failed,
                Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        mCallback.showLoading()
    }

    override fun showAuthenticationSucceed() {
        Toast.makeText(this.context, R.string.login_sucessful, Toast.LENGTH_SHORT).show()
        startActivity<MainMenuActivity>()
        mCallback.finishActivity()
    }

    interface ILoginFragmentCallbacks {
        fun onClickBackButton()

        fun finishActivity()

        fun showLoading()
    }


}// Required empty public constructor
