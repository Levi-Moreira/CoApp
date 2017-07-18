package br.edu.ifce.lds.coapp.plans.entities

import com.orm.SugarRecord

/**
 * Created by levi on 04/07/17.
 */

enum class ItemType {
    consumable,
    service
}

class ItemPlan(val item: Item, val quantity: Int) {

}

class Item(val name: String,val description: String, val type: ItemType) : SugarRecord<Item>() {

}