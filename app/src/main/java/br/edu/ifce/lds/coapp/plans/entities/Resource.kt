package br.edu.ifce.lds.coapp.plans.entities

import com.orm.SugarRecord

/**
 * Created by levi on 04/07/17.
 */

class ResourcePlan(resource: Resource, quantity_hours: Int)

class Resource(name: String, description: String, price: Float) : SugarRecord<Resource>() {

}