package com.gojek.parking.processor

import com.gojek.parking.exception.Exceptions._

import scala.io.{BufferedSource, Source}

class FileProcessor(filePath: Option[String]) extends CommandProcessor(filePath) {

  override def process: Unit = {
    val bufferedSource: BufferedSource = Source.fromFile(filePath.get)
    for (line <- bufferedSource.getLines) {
      try {
        parse(line)
      } catch {
        case invalidCommand: InvalidCommand =>
          println(invalidCommand.message)
        case parkingIssue: ParkingLotException =>
          println(parkingIssue.message)
      }
    }
    bufferedSource.close()
  }
}
