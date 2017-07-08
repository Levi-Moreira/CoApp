package br.edu.ifce.lds.coapp.plans.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.NestedScrollingParent
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.presenter.PlanDetailPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil

class PlanDetailsActivity : BaseActivity(), PlanDetailView {

    lateinit var mPresenter: PlanDetailPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_details)

        mPresenter = PlanDetailPresenter(this, PreferencesUtil(this))

        val planId = intent.extras[PLAN_ID]

        if (planId != null) {
            mPresenter.getPlan(planId as String)
        }
    }



    override fun requestError() {

    }

    override fun renderPlan(plan: Plan) {

    }

    override fun connectionError() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
