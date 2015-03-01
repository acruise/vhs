package com.cluonflux.vhs.model

import java.util.UUID

case class Guid[+T](raw: UUID) extends AnyVal

object Guid {
  def apply[T](s: String): Guid[T] = Guid[T](UUID.fromString(s))
  def apply[T](): Guid[T] = Guid[T](UUID.randomUUID())
}
