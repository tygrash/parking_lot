package com.gojek.parking.exception

object Exceptions {

  final case class InvalidCommand(
                                   message: String,
                                   reason: Throwable = None.orNull) extends Exception(message, reason)

  final case class ParkingLotException(
                                        message: String,
                                        reason: Throwable = None.orNull) extends Exception(message, reason)

}
