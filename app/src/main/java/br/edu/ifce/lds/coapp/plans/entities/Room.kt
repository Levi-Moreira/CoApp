package br.edu.ifce.lds.coapp.plans.entities

/**
 * Created by levi on 04/07/17.
 */

enum class RoomType {
    shared, private
}

class RoomPlan(room: Room, quantity_hours: Int)

class Room(name: String, description: String, price_hour: Double, price_month: Double, type: RoomType) {

}