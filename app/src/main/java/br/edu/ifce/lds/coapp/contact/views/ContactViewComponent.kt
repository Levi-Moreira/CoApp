package br.edu.ifce.lds.coapp.contact.views

import br.edu.ifce.lds.coapp.utils.ContactScope
import dagger.Subcomponent

/**
 * Created by lds on 7/13/17.
 */
@ContactScope
@Subcomponent(modules = arrayOf(ContactActivityModule::class))
interface ContactViewComponent {
    fun inject(activity: ContactActivity)
}