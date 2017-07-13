package br.edu.ifce.lds.coapp.contact.views

import br.edu.ifce.lds.coapp.utils.ContactScope
import dagger.Module
import dagger.Provides

/**
 * Created by lds on 7/13/17.
 */
@Module class ContactViewModule(val mView: ContactView) {

    @ContactScope
    @Provides
    fun provideView(): ContactView = mView
}