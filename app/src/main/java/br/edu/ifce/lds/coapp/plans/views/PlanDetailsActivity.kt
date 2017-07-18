package br.edu.ifce.lds.coapp.plans.views

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import br.edu.ifce.lds.coapp.plans.adapters.ItemsListAdapter
import br.edu.ifce.lds.coapp.plans.adapters.ResourcesListAdapter
import br.edu.ifce.lds.coapp.plans.adapters.RoomsListAdapter
import br.edu.ifce.lds.coapp.plans.adapters.ServicesListAdapter
import br.edu.ifce.lds.coapp.plans.entities.*
import br.edu.ifce.lds.coapp.plans.presenter.PlanDetailPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import kotlinx.android.synthetic.main.activity_plan_details.*
import me.relex.circleindicator.CircleIndicator

class PlanDetailsActivity : BaseActivity(), PlanDetailView {

    lateinit var mPresenter: PlanDetailPresenter

    lateinit var mResourcesAdapter: ResourcesListAdapter
    lateinit var mItemsAdapter: ItemsListAdapter
    lateinit var mRoomsAdapter: RoomsListAdapter
    lateinit var mServicesAdapter: ServicesListAdapter

    var mResources = mutableListOf<ResourcePlan>()
    var mItems = mutableListOf<ItemPlan>()
    var mServices = mutableListOf<ItemPlan>()
    var mRooms = mutableListOf<RoomPlan>()

    lateinit var circleIndicator: CircleIndicator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan_details)

        mPresenter = PlanDetailPresenter(this, PreferencesUtil(this))

        mResourcesAdapter = ResourcesListAdapter(mResources)
        resourcesList.adapter = mResourcesAdapter
        resourcesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        resourcesList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))


        mItemsAdapter = ItemsListAdapter(mItems)
        itemsList.adapter = mItemsAdapter
        itemsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        itemsList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        mServicesAdapter = ServicesListAdapter(mServices)
        servicesList.adapter = mServicesAdapter
        servicesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        servicesList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        mRoomsAdapter = RoomsListAdapter(mRooms)
        pagerRooms.adapter = mRoomsAdapter
        indicator.setViewPager(pagerRooms)
        mRoomsAdapter.registerDataSetObserver(indicator.dataSetObserver)

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

        if (mResources.size > 0) {

            mResourcesAdapter.notifyDataSetChanged()
        } else
            hideResources()


        mRooms.clear()
        mRooms.addAll(plan.rooms)

        if (mRooms.size > 0)
            mRoomsAdapter.notifyDataSetChanged()
        else
            hideRooms()


        mItems.clear()
        mItems.addAll(plan.items.filter { it.item.type == ItemType.consumable })

        if (mItems.size > 0)
            mItemsAdapter.notifyDataSetChanged()
        else
            hideItems()

        mServices.clear()
        mServices.addAll(plan.items.filter { it.item.type == ItemType.service })

        if (mServices.size > 0)
            mServicesAdapter.notifyDataSetChanged()
        else
            hideServices()


    }

    private fun hideRooms() {
        rooms.visibility = View.GONE
        pagerRooms.visibility = View.GONE
    }

    private fun hideServices() {
        services.visibility = View.GONE
        servicesCard.visibility = View.GONE
    }

    private fun hideItems() {
        items.visibility = View.GONE
        itemsCard.visibility = View.GONE
    }

    private fun hideResources() {
        resourcesT.visibility = View.GONE
        resourceCard.visibility = View.GONE
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
