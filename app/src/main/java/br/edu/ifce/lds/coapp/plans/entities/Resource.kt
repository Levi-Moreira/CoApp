package br.edu.ifce.lds.coapp.plans.entities

import com.orm.SugarRecord

/**
 * Created by levi on 04/07/17.
 */

class ResourcePlan(val resource: Resource, val quantity_hours: Int)

class Resource(val name: String, val description: String, val price: Float) : SugarRecord<Resource>() {

}