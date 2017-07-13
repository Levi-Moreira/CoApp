package br.edu.ifce.lds.coapp.contact.views

import br.edu.ifce.lds.coapp.application.ApplicationModule
import br.edu.ifce.lds.coapp.utils.ContactScope
import dagger.Component

/**
 * Created by lds on 7/13/17.
 */
@ContactScope
@Component(modules = arrayOf(ContactViewModule::class, ApplicationModule::class))
interface ContactViewComponent {
    fun inject(activity: ContactActivity)
}