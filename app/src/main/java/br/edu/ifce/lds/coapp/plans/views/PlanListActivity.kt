package br.edu.ifce.lds.coapp.plans.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.plans.adapters.PlanListAdapter
import br.edu.ifce.lds.coapp.plans.entities.Plan
import br.edu.ifce.lds.coapp.plans.presenter.PlanListPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_plan_list.*

class PlanListActivity : BaseActivity(), PlanListView, PlanListAdapter.OnClickPostListener {

    lateinit var mPresenter: PlanListPresenter

    var mPlanList = mutableListOf<Plan>()
    var mAdapter = PlanListAdapter(this, mPlanList, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_list)

        mPresenter = PlanListPresenter(this, PreferencesUtil(this))
        mPresenter.getPlans()
    }


    override fun renderPlans(plans: ArrayList<Plan>) {
        mPlanList.addAll(plans)

        planList.adapter = mAdapter
        planList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickPost(position: Int) {

    }

    override fun showLoading() {
        progressWheel.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressWheel.visibility = View.GONE
    }
}
