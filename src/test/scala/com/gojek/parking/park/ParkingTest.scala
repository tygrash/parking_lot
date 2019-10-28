package com.gojek.parking.park

import com.gojek.parking.models.parking_lot.ParkingLot
import com.gojek.parking.models.vehicles.{Car, Vehicle}
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable

class ParkingTest extends FlatSpec with Matchers {

  private object fixtures {
    val car_1: Car = Car("KA-01-AB-9876", "blue")
    val car_2: Car = Car("KA-02-CB-5176", "white")
    val car_3: Car = Car("KA-01-BH-9081", "black")
    val car_4: Car = Car("KA-05-GP-7614", "blue")

    var pod_map: mutable.HashMap[Int, Vehicle] = mutable.HashMap.empty[Int, Vehicle]
    var empty_slots: mutable.TreeSet[Int] = mutable.TreeSet.empty[Int]

    pod_map.put(1, car_1)
    pod_map.put(2, car_2)

    empty_slots.add(3)

    var parking: Parking = new Parking
  }

  it should "create a ParkingLot object" in {
    import fixtures._
    parking.create(3)
    parking.parking_lot shouldBe a[ParkingLot]
  }

  it should "park a Car in ParkingLot" in {
    import fixtures._
    parking.create(3)
    parking.park(car_1)

    parking.parking_lot.empty_slots.size shouldEqual 2
  }

  it should "not park a Car in ParkingLot" in {
    import fixtures._
    parking.create(3)
    parking.park(car_1)
    parking.park(car_2)
    parking.park(car_3)

    parking.parking_lot.empty_slots.size shouldEqual 0
    parking.park(car_4)
    parking.parking_lot.empty_slots.size shouldEqual 0
  }

  it should "increase empty slots when car leaves" in {
    import fixtures._
    parking.create(2)
    parking.park(car_1)
    parking.park(car_2)

    parking.parking_lot.empty_slots.size shouldEqual 0
    parking.leave(1)
    parking.parking_lot.empty_slots.size shouldEqual 1
  }

  it should "not allow to empty slots when invalid slot number is given" in {
    import fixtures._
    parking.create(2)
    parking.park(car_1)
    parking.park(car_2)

    parking.parking_lot.empty_slots.size shouldEqual 0
    parking.leave(0)
    parking.parking_lot.empty_slots.size shouldEqual 0
  }
}
