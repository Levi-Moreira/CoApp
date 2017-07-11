package br.edu.ifce.lds.coapp.plans.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.plans.entities.ResourcePlan
import kotlinx.android.synthetic.main.resources_list_item.view.*

/**
 * Created by lds on 7/11/17.
 */


class ResourcesListAdapter(val mResources: MutableList<ResourcePlan>) : RecyclerView.Adapter<ResourcesListAdapter.ResourcesListViewHolder>() {




    override fun onBindViewHolder(holder: ResourcesListViewHolder, position: Int) {
        holder.view.resourceName.text = mResources[position].resource.name
        holder.view.resourceQuantity.text = mResources[position].quantity_hours.toString()+ " hora(s)"
    }

    override fun getItemCount(): Int = mResources.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ResourcesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.resources_list_item, parent, false)
        return ResourcesListViewHolder(view)
    }

    inner class ResourcesListViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}