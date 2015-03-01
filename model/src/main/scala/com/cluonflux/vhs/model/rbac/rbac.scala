package com.cluonflux.vhs.model.rbac

import com.cluonflux.vhs.model.people.User
import com.cluonflux.vhs.model.{EntityType, Guid}

/** A named collection of permissions that can be assigned/granted to users */
case class Role(guid: Guid[Role], name: String, permissions: Seq[Permission])

/** An assignment/grant of some role to some user */
case class RoleAssignment(guid: Guid[RoleAssignment], userGuid: Guid[User], roleGuid: Guid[Role])

/**
 * A permission granted by a role
 *
 * @param entityType the type of entity this permission applies to
 * @param operation  the method that this permission grants the ability to do
 * @param scope      identifies the set of entities that the permission applies to
 */
case class Permission(guid: Guid[Permission], roleGuid: Guid[Role], entityType: EntityType, operation: Operation, scope: Scope)

