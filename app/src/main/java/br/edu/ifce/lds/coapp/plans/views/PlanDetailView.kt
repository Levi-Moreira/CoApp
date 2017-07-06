package br.edu.ifce.lds.coapp.plans.views

import br.edu.ifce.lds.coapp.common.BaseView
import br.edu.ifce.lds.coapp.plans.entities.Plan

/**
 * Created by levi on 06/07/17.
 */
interface PlanDetailView : BaseView {
    fun requestError()
    fun renderPlan(plan: Plan)
    fun connectionError()
    fun showLoading()
    fun hideLoading()

}