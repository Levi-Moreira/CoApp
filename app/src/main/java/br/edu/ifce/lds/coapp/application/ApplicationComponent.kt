package br.edu.ifce.lds.coapp.application

import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.contact.views.ContactActivity
import br.edu.ifce.lds.coapp.utils.PreferencesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lds on 7/13/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(activity: ContactActivity)

}