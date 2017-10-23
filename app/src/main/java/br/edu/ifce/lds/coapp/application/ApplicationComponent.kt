package br.edu.ifce.lds.coapp.application

import android.app.Application
import br.edu.ifce.lds.coapp.contact.di.ContactActivityComponent
import br.edu.ifce.lds.coapp.contact.views.ContactActivityModule
import br.edu.ifce.lds.coapp.login.di.LoginActivityComponent
import br.edu.ifce.lds.coapp.login.di.LoginActivityModule
import br.edu.ifce.lds.coapp.plans.di.PlanDetailActivityComponent
import br.edu.ifce.lds.coapp.plans.di.PlanDetailActivityModule
import br.edu.ifce.lds.coapp.plans.di.PlanListActivityComponent
import br.edu.ifce.lds.coapp.plans.di.PlanListActivityModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lds on 7/13/17.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun plus(module: ContactActivityModule): ContactActivityComponent
    fun plus(module: PlanListActivityModule): PlanListActivityComponent
    fun plus(module: PlanDetailActivityModule): PlanDetailActivityComponent
    fun plus(module: LoginActivityModule): LoginActivityComponent
    fun application(): Application
}