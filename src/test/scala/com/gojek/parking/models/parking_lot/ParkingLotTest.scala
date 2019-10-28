package com.gojek.parking.models.parking_lot

import com.gojek.parking.models.vehicles.Vehicle
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class ParkingLotTest extends FlatSpec with Matchers {

  private object fixtures {
    var pods_map: mutable.HashMap[Int, Vehicle] = mutable.HashMap.empty[Int, Vehicle]
    var empty_slots: mutable.TreeSet[Int] = mutable.TreeSet.empty[Int]
    val parking_lot_1 = ParkingLot(Option(1), Option("com.gojek.parking.models.vehicles.Car"), 2, pods_map, empty_slots)
  }

  it should "create a ParkingLot object" in {
    import fixtures._
    parking_lot_1 shouldBe a[ParkingLot]
  }

  it should "return 1 as id number" in {
    import fixtures._
    parking_lot_1.id.get shouldEqual 1
  }

  it should "not return 0 as id number" in {
    import fixtures._
    parking_lot_1.id.get != 0
  }

  it should "return com.gojek.parking.models.vehicles.Car as vehicle type" in {
    import fixtures._
    parking_lot_1.vehicle_type.get shouldEqual "com.gojek.parking.models.vehicles.Car"
  }

  it should "not return com.gojek.parking.models.vehicles.Bike as vehicle type" in {
    import fixtures._
    parking_lot_1.vehicle_type.get != "com.gojek.parking.models.vehicles.Bike"
  }

  it should "return 1 as capacity" in {
    import fixtures._
    parking_lot_1.capacity shouldEqual 2
  }

  it should "not return 1 as capacity" in {
    import fixtures._
    parking_lot_1.capacity != 1
  }

  it should "return 0 as current pod size" in {
    import fixtures._
    parking_lot_1.pods.size shouldEqual 0
  }

  it should "not return 1 as current pod size" in {
    import fixtures._
    parking_lot_1.pods.size != 1
  }
}
