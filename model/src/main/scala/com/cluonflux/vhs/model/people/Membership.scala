package com.cluonflux.vhs.model.people

import com.cluonflux.vhs.model.Guid
import com.cluonflux.vhs.model.payment.PaymentIshThing
import org.joda.time.DateTime

case class Membership(guid: Guid[Membership],
                  userGuid: Guid[User],
               paymentGuid: Guid[PaymentIshThing],
                 timestamp: Long,
                 startDate: DateTime,
                expiryDate: DateTime)