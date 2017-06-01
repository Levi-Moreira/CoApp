package br.edu.ifce.lds.coapp.landing

import com.gral.android.actsports.common.BaseView

/**
 * Interface that holds the view methods
 */
interface LoginView : BaseView {

    fun showAuthenticationFailed()

    fun showLoading()

    fun showAuthenticationSucceed()

}