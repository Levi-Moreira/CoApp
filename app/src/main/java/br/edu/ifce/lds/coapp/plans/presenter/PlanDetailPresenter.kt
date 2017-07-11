package br.edu.ifce.lds.coapp.plans.presenter

import br.edu.ifce.lds.coapp.common.BasePresenter
import br.edu.ifce.lds.coapp.plans.dhandler.PlanDetailDataHandler
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.views.PlanDetailView
import br.edu.ifce.lds.coapp.utils.PreferencesUtil

/**
 * Created by levi on 06/07/17.
 */

class PlanDetailPresenter(val mView: PlanDetailView, val prefs: PreferencesUtil) : BasePresenter<PlanDetailView> {

    var mDataHandler = PlanDetailDataHandler(this, prefs)

    fun getPlan(planId: String) {
        mView.showLoading()
        mDataHandler.getPlan(planId)
    }

    fun renderPlan(body: Plan?) {
        mView.hideLoading()
        if (body != null) {
            mView.renderPlan(body)
        }
    }

    fun requestError() {
        mView.hideLoading()
        mView.requestError()
    }

    fun connectionError() {
        mView.hideLoading()
        mView.connectionError()
    }

}