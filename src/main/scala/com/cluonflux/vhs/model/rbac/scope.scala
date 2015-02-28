package com.cluonflux.vhs.model.rbac

import com.cluonflux.vhs.model.{Folder, Guid}
import spray.json.JsValue

/** Implementations describe the scope of a permission, i.e. how to identify the applicable subset of entities */
sealed trait Scope[T] {
  def guid: Guid[_ <: Scope[T]]
  def permissionGuid: Guid[Permission]
}

/** Apply the permission to every entity of the permission's type (equivalent to an [[GeneralScope]] with a [[FolderPredicate]] pointing to the root.) */
case class WildcardScope[T](guid: Guid[WildcardScope], permissionGuid: Guid[Permission]) extends Scope[T]

/** Apply the permission to a particular, identified entity */
case class IdentityScope[T](guid: Guid[IdentityScope[T]], permissionGuid: Guid[Permission], entityGuid: Guid[T]) extends Scope[T]

/** Apply the permission to entities with attributes matching every predicate */
case class GeneralScope[T](guid: Guid[GeneralScope], permissionGuid: Guid[Permission], predicates: Seq[EntityPredicate[T]]) extends Scope[T]

/** Implementations evaluate whether a particular entity matches their rule */
sealed trait EntityPredicate[T] {
  def guid: Guid[_ <: EntityPredicate]
  def scopeGuid: Guid[GeneralScope]
}

/**
 * Check that the entity is in a particular folder.
 *
 * @param recursive true if the predicate should still succeed if the entity is in a child of `folder`.
 */
case class FolderPredicate[T](guid: Guid[FolderPredicate], scopeGuid: Guid[GeneralScope], folderGuid: Guid[Folder], recursive: Boolean) extends EntityPredicate[T]

/**
 * Check that attribute `name` has the precise value `value`
 */
case class AttributeExactPredicate[T](guid: Guid[AttributeExactPredicate], scopeGuid: Guid[GeneralScope], attributeName: String, value: JsValue) extends EntityPredicate[T]

/**
 * Check that attribute `attributeName`, when expressed as a String, matches regex `regex`.
 */
case class AttributeRegexPredicate[T](guid: Guid[AttributeRegexPredicate], scopeGuid: Guid[GeneralScope], attributeName: String, regex: String) extends EntityPredicate[T]