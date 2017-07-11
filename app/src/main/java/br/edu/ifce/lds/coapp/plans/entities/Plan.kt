package br.edu.ifce.lds.coapp.plans.entities

import com.orm.SugarRecord

/**
 * Created by levi on 04/07/17.
 */
class Plan(var name: String, var description: String, var price: Float) : SugarRecord<Plan>() {

    lateinit var resources: ArrayList<ResourcePlan>
    lateinit var rooms: ArrayList<RoomPlan>
    lateinit var items: ArrayList<ItemPlan>
}