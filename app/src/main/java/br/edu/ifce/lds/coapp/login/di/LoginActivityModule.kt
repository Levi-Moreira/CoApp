package br.edu.ifce.lds.coapp.login.di

import br.edu.ifce.lds.coapp.login.dhandlers.LoginDataHandler
import br.edu.ifce.lds.coapp.login.mvp.LoginContract
import br.edu.ifce.lds.coapp.login.presenters.LoginPresenter
import br.edu.ifce.lds.coapp.utils.LoginScope
import dagger.Module
import dagger.Provides

/**
 * Created by lds on 10/23/17.
 */

@Module
class LoginActivityModule(val mView: LoginContract.LoginView) {
    @Provides
    @LoginScope
    fun provideView(): LoginContract.LoginView = mView

    @Provides
    @LoginScope
    fun providePresenter(mPresenter: LoginPresenter): LoginContract.LoginPresenter = mPresenter

    @Provides
    @LoginScope
    fun provideDataHandler(mDataHandler: LoginDataHandler): LoginContract.LoginDataHandler = mDataHandler
}