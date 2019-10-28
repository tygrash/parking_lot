package com.gojek.parking.models.vehicles

import org.scalatest.{FlatSpec, Matchers}

class CarTest extends FlatSpec with Matchers {

  private object fixtures {
    val car_1 = Car("KA-01-AB-98769", "blue")
  }

  it should "create a Car object" in {
    import fixtures._
    car_1 shouldBe a[Car]
  }

  it should "extend a Vehicle trait" in {
    import fixtures._
    car_1 shouldBe a[Vehicle]
  }

  it should "return KA-01-AB-98769 as registration number" in {
    import fixtures._
    car_1.reg_no shouldEqual "KA-01-AB-98769"
  }

  it should "not return KA-01-AB-98768 as registration number" in {
    import fixtures._
    car_1.reg_no != "KA-01-AB-98768"
  }

  it should "return blue as registration number" in {
    import fixtures._
    car_1.colour shouldEqual "blue"
  }

  it should "not return green as registration number" in {
    import fixtures._
    car_1.colour != "green"
  }
}
