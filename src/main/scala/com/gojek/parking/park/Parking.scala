package com.gojek.parking.park

import com.gojek.parking.exception.Exceptions.ParkingLotException
import com.gojek.parking.models.parking_lot.ParkingLot
import com.gojek.parking.models.vehicles.Vehicle

import scala.collection.mutable

class Parking {

  var parking_lot: ParkingLot = _

  def create(capacity: Int): Unit = {
    var empty_slots: mutable.TreeSet[Int] = mutable.TreeSet.empty[Int]
    for (iter: Int <- 1 to capacity) {
      empty_slots.add(iter)
    }

    parking_lot = ParkingLot(None, None, capacity, mutable.HashMap.empty[Int, Vehicle], empty_slots)
    println(s"Created a parking lot with $capacity slots")
  }

  private def checkForExistingRegInPod(reg: String): Boolean = {
    var already_exists: Boolean = false
    val keys: List[Int] = parking_lot.pods.keys.toList.sorted
    keys.foreach {
      key: Int =>
        val vehicle: Vehicle = parking_lot.pods(key)
        if (vehicle.reg_no.equals(reg)) {
          already_exists = !already_exists
        }
    }

    already_exists
  }

  def park(car: Vehicle): Unit = {
    validateParkingLot()

    if (!checkForExistingRegInPod(car.reg_no)) {
      if (parking_lot.empty_slots.isEmpty) {
        println("Sorry, parking lot is full")
      } else {
        val empty_slot: Int = parking_lot.empty_slots.head
        parking_lot.pods.put(empty_slot, car)
        parking_lot.empty_slots.remove(empty_slot)
        println(s"Allocated slot number: $empty_slot")
      }
    } else {
      println("Car with same registration number is already parked")
    }
  }

  def leave(slot: Int): Unit = {
    validateParkingLot()

    if (slot > 0 && slot <= parking_lot.pods.size) {
      parking_lot.pods.remove(slot)
      parking_lot.empty_slots.add(slot)

      println(s"Slot number ${slot} is free")
    } else {
      println(s"Slot number ${slot} is not present")
    }
  }

  def status(): Unit = {
    validateParkingLot()

    println(s"Slot No.    Registration No    Colour")
    val keys: List[Int] = parking_lot.pods.keys.toList.sorted
    keys.foreach {
      key: Int =>
        val vehicle: Vehicle = parking_lot.pods(key)
        println(s"$key           ${vehicle.reg_no}      ${vehicle.colour}")
    }
  }

  def slot_for_reg(reg: String): Unit = {
    validateParkingLot()

    if (checkForExistingRegInPod(reg)) {
      val keys: List[Int] = parking_lot.pods.keys.toList.sorted
      keys.foreach {
        key: Int =>
          val vehicle: Vehicle = parking_lot.pods(key)
          if (vehicle.reg_no == reg) {
            println(s"$key")
          }
      }
    } else {
      println("Not Found")
    }
  }

  private def printResult(listOfResults: mutable.SortedSet[String]): Unit = {
    var output_string: String = ""
    listOfResults.foreach(i => output_string += i + "," + " ")
    println(output_string.dropRight(2))
  }

  def regForCarColour(colour: String): Unit = {
    validateParkingLot()

    val output_set: mutable.SortedSet[String] = mutable.SortedSet[String]()

    val keys: List[Int] = parking_lot.pods.keys.toList.sorted
    keys.foreach {
      key: Int =>
        val vehicle: Vehicle = parking_lot.pods(key)
        if (vehicle.colour == colour) {
          output_set.add(vehicle.reg_no)
        }
    }

    printResult(output_set)
  }

  def slotForCarColour(colour: String): Unit = {
    validateParkingLot()

    val output_set: mutable.SortedSet[String] = mutable.SortedSet[String]()

    val keys: List[Int] = parking_lot.pods.keys.toList.sorted
    keys.foreach {
      key: Int =>
        val vehicle: Vehicle = parking_lot.pods(key)
        if (vehicle.colour == colour) {
          output_set.add(key.toString)
        }
    }

    printResult(output_set)
  }

  private def validateParkingLot(): Unit = {
    if (parking_lot == null) {
      throw ParkingLotException("No parking lot exists")
    }
  }
}
