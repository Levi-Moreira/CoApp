package br.edu.ifce.lds.coapp.plans.dhandler

import br.edu.ifce.lds.coapp.apiservice.CoAppBackend
import br.edu.ifce.lds.coapp.plans.entities.Plan
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by levi on 06/0, z7/17.
 */
class PlanDetailDataHandlerImpl @Inject constructor() : PlanDetailDataHandler {

    val TAG = "PlanListDataHandlerImpl"

    @Inject
    lateinit var backend: CoAppBackend

    override fun getPlan(planId: String): Observable<Response<Plan>>
            = backend.backendAPI.getPlan("1", planId, backend.publicTokenFormatted)
}