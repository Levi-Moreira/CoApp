package br.edu.ifce.lds.coapp.plans.adapters

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.plans.entities.RoomPlan
import kotlinx.android.synthetic.main.room_list_item.view.*
import org.jetbrains.anko.find

/**
 * Created by lds on 7/12/17.
 */
class RoomsListAdapter(val mRooms: MutableList<RoomPlan>) : PagerAdapter() {


    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = mRooms.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = LayoutInflater.from(container.context).inflate(R.layout.room_list_item, container, false)

        view.roomName.text = mRooms[position].room.name
        view.roomHours.text = mRooms[position].quantity_hours.toString() + "hora(s)"
        container.addView(view)
        return view
    }

}