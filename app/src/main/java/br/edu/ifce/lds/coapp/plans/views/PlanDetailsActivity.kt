package br.edu.ifce.lds.coapp.plans.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.NestedScrollingParent
import android.view.View
import android.widget.Toolbar
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.presenter.PlanDetailPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_plan_details.*

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

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun requestError() {

    }

    override fun renderPlan(plan: Plan) {

        toolbar.title = plan.name


    }

    override fun connectionError() {

    }

    override fun showLoading() {
        progressWheel.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressWheel.visibility = View.INVISIBLE
    }
}
