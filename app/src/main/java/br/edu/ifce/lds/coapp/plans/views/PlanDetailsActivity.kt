package br.edu.ifce.lds.coapp.plans.views

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.plans.adapters.ResourcesListAdapter
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.entities.ResourcePlan
import br.edu.ifce.lds.coapp.plans.presenter.PlanDetailPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_plan_details.*

class PlanDetailsActivity : BaseActivity(), PlanDetailView {

    lateinit var mPresenter: PlanDetailPresenter

    lateinit var mResourcesAdapter: ResourcesListAdapter

    var mResources = mutableListOf<ResourcePlan>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_details)

        mPresenter = PlanDetailPresenter(this, PreferencesUtil(this))

        mResourcesAdapter = ResourcesListAdapter(mResources)
        resourcesList.adapter = mResourcesAdapter
        resourcesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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
        collapsingToolbar.title = plan.name

        mResources.clear()
        mResources.addAll(plan.resources)
        mResourcesAdapter.notifyDataSetChanged()


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
