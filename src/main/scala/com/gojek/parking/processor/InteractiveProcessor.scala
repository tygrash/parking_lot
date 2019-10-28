package com.gojek.parking.processor

import com.gojek.parking.exception.Exceptions.{InvalidCommand, ParkingLotException}

class InteractiveProcessor(filePath: Option[String]) extends CommandProcessor(filePath) {
  override def process: Unit = {
    while (true) {
      try {
        val input: String = scala.io.StdIn.readLine()
        if (input == "exit") {
          return
        } else {
          parse(input)
        }
      } catch {
        case invalidCommand: InvalidCommand =>
          println(invalidCommand.message)
        case parkingIssue: ParkingLotException =>
          println(parkingIssue.message)
      }
    }
  }
}
