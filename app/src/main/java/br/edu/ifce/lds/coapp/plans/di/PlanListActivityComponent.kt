package br.edu.ifce.lds.coapp.plans.di

import br.edu.ifce.lds.coapp.plans.views.PlanListActivity
import br.edu.ifce.lds.coapp.utils.PlanScope
import dagger.Subcomponent

/**
 * Created by lds on 7/18/17.
 */
@PlanScope
@Subcomponent(modules = arrayOf(PlanListActivityModule::class))
interface PlanListActivityComponent {
    fun inject(activity: PlanListActivity)
}