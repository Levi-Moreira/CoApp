package br.edu.ifce.lds.coapp.plans.presenter

import br.edu.ifce.lds.coapp.plans.dhandler.PlanListDataHandler
import br.edu.ifce.lds.coapp.plans.dhandler.PlanListDataHandlerImpl
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.views.PlanListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by levi on 04/07/17.
 */
class PlanListPresenterImpl @Inject constructor(val mView: PlanListView) : PlanListPresenter {

    @Inject
    lateinit var mDataHandler: PlanListDataHandler

    override fun getPlans() {
        mView.showLoading()
        mDataHandler.getPlanList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    plans ->

                    if (plans.isSuccessful) {
                        renderPlans(plans.body())
                    } else {
                        showRequestError()
                    }
                },
                        {
                            error ->
                            showConnectionError()

                        })
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