package com.gojek.parking.processor

import com.gojek.parking.exception.Exceptions.InvalidCommand
import com.gojek.parking.models.vehicles.{Car, Vehicle}
import com.gojek.parking.utils.Commands
import com.gojek.parking.park.Parking

abstract class CommandProcessor(filePath: Option[String]) {

  private var parking: Parking = new Parking

  @throws(classOf[Exception])
  def parse(input: String): Unit = {
    val command_list: Array[String] = input.split(" ")
    val command_length: Int = command_list.length
    if (command_length > 3 || command_length < 1) {
      throw InvalidCommand("Invalid command to park your vehicle...")
    }
    else {
      try {
        val command: String = command_list.head
        command match {
          case Commands.CREATE if command_length == 2 =>
            val capacity: Int = command_list(1).toInt
            parking.create(capacity)
          case Commands.PARK if command_length == 3 =>
            val car: Vehicle = Car(command_list(1), command_list(2))
            parking.park(car)
          case Commands.LEAVE if command_length == 2 =>
            parking.leave(command_list(1).toInt)
          case Commands.STATUS if command_length == 1 =>
            parking.status()
          case Commands.SLOT_FOR_REG if command_length == 2 =>
            parking.slot_for_reg(command_list(1))
          case Commands.REG_FOR_CAR_COLOUR if command_length == 2 =>
            parking.regForCarColour(command_list(1))
          case Commands.SLOT_FOR_CAR_COLOUR if command_length == 2 =>
            parking.slotForCarColour(command_list(1))
          case _ =>
            throw InvalidCommand("Unknown Command")
        }
      } catch {
        case _: java.lang.NumberFormatException =>
          throw InvalidCommand("Unknown Command")
      }
    }
  }

  def process
}
