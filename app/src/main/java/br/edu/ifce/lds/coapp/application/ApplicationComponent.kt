package br.edu.ifce.lds.coapp.application

import android.app.Application
import android.content.Context
import br.edu.ifce.lds.coapp.contact.views.ContactActivity
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
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