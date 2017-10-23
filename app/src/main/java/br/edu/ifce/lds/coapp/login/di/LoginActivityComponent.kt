package br.edu.ifce.lds.coapp.login.di

import br.edu.ifce.lds.coapp.login.views.LoginActivity
import br.edu.ifce.lds.coapp.utils.LoginScope
import dagger.Subcomponent

/**
 * Created by lds on 10/23/17.
 */
@LoginScope
@Subcomponent(modules = arrayOf(LoginActivityModule::class))
interface LoginActivityComponent {
    fun inject(activity: LoginActivity)
}