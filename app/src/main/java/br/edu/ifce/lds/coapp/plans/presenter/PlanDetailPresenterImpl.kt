package br.edu.ifce.lds.coapp.plans.presenter

import br.edu.ifce.lds.coapp.plans.dhandler.PlanDetailDataHandler
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.views.PlanDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by levi on 06/07/17.
 */

class PlanDetailPresenterImpl @Inject constructor(val mView: PlanDetailView) : PlanDetailPresenter {

    @Inject lateinit var mDataHandler: PlanDetailDataHandler

    override fun getPlan(planId: String) {
        mView.showLoading()
        mDataHandler.getPlan(planId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    body ->
                    if (body.isSuccessful) {
                        renderPlan(body.body())
                    } else {
                        requestError()
                    }
                }, {
                    erro ->
                    connectionError()
                })
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