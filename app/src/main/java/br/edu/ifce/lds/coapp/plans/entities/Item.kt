package br.edu.ifce.lds.coapp.plans.entities

import com.orm.SugarRecord

/**
 * Created by levi on 04/07/17.
 */

enum class ItemType {
    consumable
}

class ItemPlan(item: Item, quantity: Int) {

}

class Item(name: String, description: String, type: ItemType) : SugarRecord<Item>() {

}