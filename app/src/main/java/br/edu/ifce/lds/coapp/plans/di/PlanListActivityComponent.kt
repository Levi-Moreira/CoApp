package br.edu.ifce.lds.coapp.plans.di

import br.edu.ifce.lds.coapp.plans.views.PlanListActivity
import br.edu.ifce.lds.coapp.utils.ContactScope
import dagger.Subcomponent

/**
 * Created by lds on 7/18/17.
 */
@ContactScope
@Subcomponent(modules = arrayOf(PlanListActivityModule::class))
interface PlanListActivityComponent {
    fun inject(activity: PlanListActivity)
}