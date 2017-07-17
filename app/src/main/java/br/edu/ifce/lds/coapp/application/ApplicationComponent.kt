package br.edu.ifce.lds.coapp.application

import android.app.Application
import br.edu.ifce.lds.coapp.contact.views.ContactActivityModule
import br.edu.ifce.lds.coapp.contact.views.ContactViewComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lds on 7/13/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun plus(module: ContactActivityModule): ContactViewComponent
    fun application(): Application
}