package br.edu.ifce.lds.coapp.plans.di

import br.edu.ifce.lds.coapp.plans.views.PlanDetailsActivity
import br.edu.ifce.lds.coapp.plans.views.PlanListActivity
import br.edu.ifce.lds.coapp.utils.PlanScope
import dagger.Subcomponent

/**
 * Created by lds on 7/18/17.
 */
@PlanScope
@Subcomponent(modules = arrayOf(PlanDetailActivityModule::class))
interface PlanDetailActivityComponent {
    fun inject(activity: PlanDetailsActivity)
}