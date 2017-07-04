package br.edu.ifce.lds.coapp.plans

import com.orm.SugarRecord

/**
 * Created by levi on 04/07/17.
 */
class Plan(name: String, description: String, price: Float) : SugarRecord<Plan>() {

    lateinit var resurces : ArrayList<Resource>
    lateinit var rooms : ArrayList<Room>
    lateinit var items : ArrayList<Item>
}