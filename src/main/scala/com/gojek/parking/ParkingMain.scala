package com.gojek.parking

import java.io.FileNotFoundException

import com.gojek.parking.processor.{CommandProcessor, FileProcessor, InteractiveProcessor}

object ParkingMain {

  def main(args: Array[String]): Unit = {

    args.length match {
      case 0 =>
        // interactive mode
        val cmdProcessor: CommandProcessor = new InteractiveProcessor(None)
        cmdProcessor.process
      case 1 =>
        // File reader
        val fileProcessor: CommandProcessor = new FileProcessor(Some(args(0)))
        try {
          fileProcessor.process
        } catch {
          case e: FileNotFoundException => println(s"No file found by ${args(0)}")
        }
      case _ => println("Please give input in correct format")
    }
  }
}
