package br.edu.ifce.lds.coapp.plans.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun renderPlan(plan: Plan) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun connectionError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
