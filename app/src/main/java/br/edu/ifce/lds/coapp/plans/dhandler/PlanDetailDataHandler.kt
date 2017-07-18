package br.edu.ifce.lds.coapp.plans.dhandler

import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.plans.presenter.PlanDetailPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by levi on 06/0, z7/17.
 */
class PlanDetailDataHandler(val presenter: PlanDetailPresenter, val mPrefs: PreferencesUtil) {

    val TAG = "PlanListDataHandlerImpl"

    val backend = CoAppBackend()

    fun getPlan(planId: String) {

        backend.backendAPI
                .getPlan("1", planId, backend.publicTokenFormatted)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    body ->
                    if(body.isSuccessful){
                        presenter.renderPlan(body.body())
                    }else{
                        presenter.requestError()
                    }
                },{
                   erro ->
                    presenter.connectionError()
                })
    }
}