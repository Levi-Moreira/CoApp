package br.edu.ifce.lds.coapp.plans.entities

/**
 * Created by levi on 04/07/17.
 */

enum class RoomType {
    shared, private
}

class RoomPlan(val room: Room, val quantity_hours: Int)

class Room(val name: String, val description: String, val price_hour: Double, val price_month: Double, val type: RoomType) {

}