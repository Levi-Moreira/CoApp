package br.edu.ifce.lds.coapp.utils

import android.content.Context
import br.edu.ifce.lds.coapp.application.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lds on 7/13/17.
 */
@Singleton @Component(modules = arrayOf(ApplicationModule::class, PreferencesModule::class))

interface PreferencesComponent {
    fun inject(context: Context)
}