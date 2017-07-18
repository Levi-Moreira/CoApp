package br.edu.ifce.lds.coapp.plans.presenter

import br.edu.ifce.lds.coapp.common.BasePresenter
import br.edu.ifce.lds.coapp.plans.views.PlanListView

/**
 * Created by lds on 7/18/17.
 */
interface PlanListPresenter : BasePresenter<PlanListView>{
    fun getPlans()
}