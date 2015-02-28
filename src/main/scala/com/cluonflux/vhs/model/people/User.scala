package com.cluonflux.vhs.model.people

import com.cluonflux.vhs.model.Guid

case class User(guid: Guid[User], name: String, emails: Seq[Email])

case class Email(guid: Guid[Email], userGuid: Guid[User], address: String, confirmed: Boolean)