package br.edu.ifce.lds.coapp.plans.dhandler

import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by levi on 04/07/17.
 */

class PlanListDataHandler(val presenter: PlanListPresenter, val mPrefs: PreferencesUtil) {
    val TAG = "PlanListDataHandler"

    val backend = CoAppBackend()

    fun getPlanList() {
        backend
                .backendAPI.getPlanList("1", backend.publicTokenFormatted)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    plans ->

                    if (plans.isSuccessful) {
                        presenter.renderPlans(plans.body())
                    } else {
                        presenter.showRequestError()
                    }
                },
                        {
                            error ->
                            presenter.showConnectionError()

                        })
    }

}
