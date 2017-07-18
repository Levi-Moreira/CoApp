package br.edu.ifce.lds.coapp.contact.views

import br.edu.ifce.lds.coapp.contact.dhandlers.ContactDataHandler
import br.edu.ifce.lds.coapp.contact.dhandlers.IContactDataHandler
import br.edu.ifce.lds.coapp.contact.presenter.ContactPresenter
import br.edu.ifce.lds.coapp.contact.presenter.IContactPresenter
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
    fun providePresenter(mPresenter: ContactPresenter): IContactPresenter = mPresenter

    @Provides
    @ContactScope
    fun provideDataHandler(mDataHandler: ContactDataHandler): IContactDataHandler = mDataHandler
}