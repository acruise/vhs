package com.cluonflux.vhs.model.payment

import com.cluonflux.vhs.model.Guid
import com.cluonflux.vhs.model.people.User

sealed trait PaymentIshThing {
  def guid: Guid[_ <: PaymentIshThing]
  def timestamp: Long
  def note: String
}

case class Payment(guid: Guid[Payment], paidBy: Guid[User], timestamp: Long, amount: BigDecimal, note: String) extends PaymentIshThing

case class Waiver(guid: Guid[Waiver], waivedBy: Guid[User], timestamp: Long, note: String) extends PaymentIshThing
