package br.edu.ifce.lds.coapp.plans.entities

import com.orm.SugarRecord

/**
 * Created by levi on 04/07/17.
 */
class Plan(name: String, description: String, price: Float) : SugarRecord<Plan>() {

    lateinit var resurces: ArrayList<ResourcePlan>
    lateinit var rooms: ArrayList<RoomPlan>
    lateinit var items: ArrayList<ItemPlan>
}