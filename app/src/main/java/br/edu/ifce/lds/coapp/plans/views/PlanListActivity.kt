package br.edu.ifce.lds.coapp.plans.views

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.plans.adapters.PlanListAdapter
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_plan_list.*
import org.jetbrains.anko.startActivity

val PLAN_ID = "PLAN_ID"

class PlanListActivity : BaseActivity(), PlanListView, PlanListAdapter.OnClickPostListener {


    val PLAN_ID = "PLAN_ID"

    lateinit var mPresenter: PlanListPresenter

    var mPlanList = mutableListOf<Plan>()
    var mAdapter = PlanListAdapter(this, mPlanList, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_list)
        setTitle(R.string.our_plans)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPresenter = PlanListPresenter(this, PreferencesUtil(this))
        mPresenter.getPlans()
    }


    override fun renderPlans(plans: ArrayList<Plan>) {
        mPlanList.addAll(plans)

        planList.adapter = mAdapter
        planList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickPost(position: Int) {
        val id = mPlanList[position].id

        startActivity<PlanDetailsActivity>(PLAN_ID to id.toString())
    }

    override fun showLoading() {
        progressWheel.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressWheel.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            android.R.id.home -> onBackPressed()
        }

        return true
    }
}
