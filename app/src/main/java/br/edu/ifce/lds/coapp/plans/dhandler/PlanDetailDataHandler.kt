package br.edu.ifce.lds.coapp.plans.dhandler

import br.edu.ifce.lds.coapp.plans.entities.Plan
import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by lds on 7/18/17.
 */
interface PlanDetailDataHandler {
    fun getPlan(planId: String): Observable<Response<Plan>>
}