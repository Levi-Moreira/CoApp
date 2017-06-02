package br.edu.ifce.lds.coapp.landing.views

import br.edu.ifce.lds.coapp.common.BaseView

/**
 * Interface that holds the view methods
 */
interface LoginView : BaseView {

    fun showAuthenticationFailed()

    fun showLoading()

    fun showAuthenticationSucceed()

    fun hideLoading()

}