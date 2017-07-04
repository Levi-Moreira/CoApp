package br.edu.ifce.lds.coapp.plans.presenter

import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.views.PlanListView

/**
 * Created by levi on 04/07/17.
 */
class PlanListPresenter(val mView: PlanListView) {

    fun showConnectionError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showRequestError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun renderPlans(plans: ArrayList<Plan>?) {
        if (plans != null) {
            mView.renderPlans(plans)
        }
    }

}