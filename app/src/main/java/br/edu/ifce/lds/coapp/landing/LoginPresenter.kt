package br.edu.ifce.lds.coapp.landing

import com.gral.android.actsports.common.BasePresenter

/**
 * Created by ellca on 01/06/2017.
 */

class LoginPresenter : BasePresenter<LoginView> {

    lateinit var mView: LoginView


    override fun attachView(view: LoginView) {
        mView = view;
    }

    fun authenticationFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun authenticationSuceeded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}