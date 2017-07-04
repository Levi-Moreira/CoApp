package br.edu.ifce.lds.coapp.plans.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.plans.entities.Plan

class PlanListActivity : BaseActivity(), PlanListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_list)

    }


    override fun renderPlans(plans: ArrayList<Plan>) {

    }
}
