package br.edu.ifce.lds.coapp.contact.di

import br.edu.ifce.lds.coapp.contact.views.ContactActivityModule
import br.edu.ifce.lds.coapp.utils.ContactScope
import dagger.Subcomponent

/**
 * Created by lds on 7/13/17.
 */
@ContactScope
@Subcomponent(modules = arrayOf(ContactActivityModule::class))
interface ContactActivityComponent {
    fun inject(activity: ContactActivity)
}