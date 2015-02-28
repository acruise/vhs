package com.cluonflux.vhs.model.rbac

import spray.json.{JsValue, JsonFormat}

import com.cluonflux.vhs.model.{Folder, Guid}

/** Implementations describe the scope of a permission, i.e. the subset of entities that it applies to */
sealed trait Scope {
  def guid: Guid[_ <: Scope]
  def permissionGuid: Guid[Permission]
}

/** Apply the permission to every entity of the permission's type (equivalent to an [[GeneralScope]] with a [[FolderPredicate]] pointing to the root.) */
case class WildcardScope(guid: Guid[WildcardScope], permissionGuid: Guid[Permission]) extends Scope

/** Apply the permission to a particular, identified entity */
case class IdentityScope[E](guid: Guid[IdentityScope[E]], permissionGuid: Guid[Permission], entityGuid: Guid[E]) extends Scope

/** Apply the permission to entities matching every predicate */
case class GeneralScope(guid: Guid[GeneralScope], permissionGuid: Guid[Permission], predicates: Seq[EntityPredicate]) extends Scope

/** Implementations evaluate whether a particular entity matches their rule */
sealed trait EntityPredicate {
  def guid: Guid[_ <: EntityPredicate]
  def scopeGuid: Guid[GeneralScope]
}

/**
 * Check that the entity is in a particular folder.
 *
 * @param recursive true if the predicate should still succeed if the entity is in a child of `folder`.
 */
case class FolderPredicate(guid: Guid[FolderPredicate], scopeGuid: Guid[GeneralScope], folderGuid: Guid[Folder], recursive: Boolean) extends EntityPredicate

/**
 * Check that attribute `name` has the precise value `value`
 *
 * @tparam V the type of the entity property; must be parseable from JSON.
 */
case class AttributeExactPredicate[V : JsonFormat](guid: Guid[AttributeExactPredicate[V]], scopeGuid: Guid[GeneralScope], attributeName: String, value: JsValue) extends EntityPredicate

/**
 * Check that attribute `attributeName`, when expressed as a String, matches regex `regex`.
 */
case class AttributeRegexPredicate(guid: Guid[AttributeRegexPredicate], scopeGuid: Guid[GeneralScope], attributeName: String, regex: String) extends EntityPredicate