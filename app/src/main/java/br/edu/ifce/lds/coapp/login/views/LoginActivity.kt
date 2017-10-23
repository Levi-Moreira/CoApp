package br.edu.ifce.lds.coapp.login.views

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.application.CoAppApplication
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.di.ContactActivity
import br.edu.ifce.lds.coapp.contact.views.ContactActivityModule
import br.edu.ifce.lds.coapp.login.di.LoginActivityModule
import br.edu.ifce.lds.coapp.login.mvp.LoginContract
import br.edu.ifce.lds.coapp.utils.isValid
import br.edu.ifce.lds.coapp.utils.isValidEmail
import com.bumptech.glide.Glide
import com.facebook.FacebookSdk
import kotlinx.android.synthetic.main.activity_login2.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.LoginView {

    @Inject lateinit var mPresenter: LoginContract.LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //start up facebook sdk before creating views
        FacebookSdk.sdkInitialize(this.applicationContext)
        setContentView(R.layout.activity_login2)

        Glide
                .with(this)
                .load(R.drawable.office_background)
                .centerCrop()
                .into(backgroundImage)

        CoAppApplication
                .getApplication(this)
                .appComponent
                .plus(LoginActivityModule(this))
                .inject(this)


        contactButton.onClick {
            startActivity<ContactActivity>()
        }

        signIn.onClick {
            if (!edtEmail.isValid) {
                edtEmail.error = "Invalid email address."
                edtEmail.invalidate()
            }

            if (!edtPassword.isValid) {
                edtPassword.error = "Invalid password"
                edtEmail.invalidate()
            }
            if (edtEmail.isValid && edtPassword.isValid) {
                mPresenter.signUserIn(edtEmail.text.toString(), edtPassword.text.toString())
            } else {
                return@onClick
            }
        }
    }

    override fun showLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connetionError() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signInSuccess() {
        Toast.makeText(this, R.string.login_sucessful, Toast.LENGTH_LONG).show()
    }

    override fun signInError() {
        Snackbar.make(screen, R.string.auth_failed, Snackbar.LENGTH_SHORT).show()
    }
}
