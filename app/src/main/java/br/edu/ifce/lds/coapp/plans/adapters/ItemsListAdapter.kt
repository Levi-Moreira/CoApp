package br.edu.ifce.lds.coapp.plans.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.plans.entities.ItemPlan
import kotlinx.android.synthetic.main.items_list_item.view.*

/**
 * Created by lds on 7/11/17.
 */


class ItemsListAdapter(val mItems: MutableList<ItemPlan>) : RecyclerView.Adapter<ItemsListAdapter.ItemsListViewHolder>() {

    override fun onBindViewHolder(holder: ItemsListViewHolder, position: Int) {
        holder.view.itemName.text = mItems[position].item.name
        holder.view.itemQuantity.text = mItems[position].quantity.toString() + " unidades"
    }

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_list_item, parent, false)
        return ItemsListViewHolder(view)
    }

    inner class ItemsListViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}