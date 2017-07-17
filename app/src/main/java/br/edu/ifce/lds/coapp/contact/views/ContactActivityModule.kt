package br.edu.ifce.lds.coapp.contact.views

import br.edu.ifce.lds.coapp.contact.presenter.ContactPresenter
import br.edu.ifce.lds.coapp.utils.ContactScope
import dagger.Module
import dagger.Provides

/**
 * Created by lds on 7/13/17.
 */
@Module class ContactActivityModule(val mView: ContactView) {

    @Provides
    @ContactScope
    fun provideView(): ContactView = mView

    @Provides
    @ContactScope
    fun providePresenter(mPresenter: ContactPresenter): ContactPresenter = mPresenter
}