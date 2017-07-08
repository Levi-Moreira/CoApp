package br.edu.ifce.lds.coapp.plans.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.plans.entities.Plan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.plan_item.view.*

/**
 * Created by levi on 04/07/17.
 */

class PlanListAdapter(val context: Context, var plans: MutableList<Plan>, val clickCallback: OnClickPostListener) : RecyclerView.Adapter<PlanListAdapter.PlanListViewHolder>() {


    val placeholders = mutableListOf<Int>(R.drawable.office_space, R.drawable.office_space_2, R.drawable.office_space_3)

    override fun getItemCount(): Int = plans.size


    override fun onBindViewHolder(holder: PlanListViewHolder?, position: Int) {
        holder?.itemView?.planDescription?.text = plans[position].description
        holder?.itemView?.planName?.text = plans[position].name
        holder?.itemView?.planPrice?.text = "R$ " + plans[position].price.toString()

        if (position < placeholders.size)
            Glide.with(context).load(placeholders[position]).into(holder?.itemView?.planImage)
        else
            Glide.with(context).load(placeholders[0]).into(holder?.itemView?.planImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, position: Int): PlanListViewHolder {
        val postView = LayoutInflater.from(parent?.context).inflate(R.layout.plan_item, parent, false)
        return PlanListViewHolder(postView)
    }

    inner class PlanListViewHolder(val item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {

        init {
            item.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickCallback.onClickPost(adapterPosition)
        }
    }

    interface OnClickPostListener {
        fun onClickPost(position: Int)
    }
}