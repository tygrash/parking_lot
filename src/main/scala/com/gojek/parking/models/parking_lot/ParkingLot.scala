package com.gojek.parking.models.parking_lot

import com.gojek.parking.models.vehicles.Vehicle

import scala.collection.mutable

case class ParkingLot(
                       id: Option[Int],
                       vehicle_type: Option[String],
                       capacity: Int,
                       pods: mutable.HashMap[Int, Vehicle],
                       empty_slots: mutable.TreeSet[Int]
                     )
