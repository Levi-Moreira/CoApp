package br.edu.ifce.lds.coapp.plans.presenter

import br.edu.ifce.lds.coapp.plans.dhandler.PlanListDataHandler
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.views.PlanListView
import br.edu.ifce.lds.coapp.utils.PreferencesUtil

/**
 * Created by levi on 04/07/17.
 */
class PlanListPresenter(val mView: PlanListView, val prefs: PreferencesUtil) {

    val mDataHandler = PlanListDataHandler(this, prefs)

    fun getPlans() {
        mView.showLoading()
        mDataHandler.getPlanList()
    }

    fun showConnectionError() {
        mView.hideLoading()
    }

    fun showRequestError() {
        mView.hideLoading()
    }

    fun renderPlans(plans: ArrayList<Plan>?) {
        mView.hideLoading()
        if (plans != null) {
            mView.renderPlans(plans)
        }
    }

}