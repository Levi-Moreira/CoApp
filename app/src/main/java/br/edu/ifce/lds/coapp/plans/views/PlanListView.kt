package br.edu.ifce.lds.coapp.plans.views

import br.edu.ifce.lds.coapp.common.BaseView
import br.edu.ifce.lds.coapp.plans.entities.Plan

/**
 * Created by levi on 04/07/17.
 */

interface PlanListView : BaseView {
    fun renderPlans(plans: ArrayList<Plan>)
}