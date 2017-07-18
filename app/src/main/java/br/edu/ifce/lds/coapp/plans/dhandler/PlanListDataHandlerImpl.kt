package br.edu.ifce.lds.coapp.plans.dhandler

import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenterImpl
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by levi on 04/07/17.
 */

class PlanListDataHandlerImpl @Inject constructor() : PlanListDataHandler {
    val TAG = "PlanListDataHandlerImpl"

    @Inject
    lateinit var backend: CoAppBackend

    override fun getPlanList(): Observable<Response<ArrayList<Plan>>> = backend.backendAPI.getPlanList("1", backend.publicTokenFormatted)

}
