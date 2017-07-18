package br.edu.ifce.lds.coapp.plans.presenter

import br.edu.ifce.lds.coapp.common.BasePresenter
import br.edu.ifce.lds.coapp.plans.views.PlanDetailView

/**
 * Created by lds on 7/18/17.
 */
interface PlanDetailPresenter : BasePresenter<PlanDetailView> {
    fun getPlan(planId: String)
}