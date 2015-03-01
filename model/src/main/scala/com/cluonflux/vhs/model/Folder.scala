package com.cluonflux.vhs.model

/**
 * @param parentGuid  if this isn't the root folder, the guid of its parent goes here.
 * @param entityTypes if this folder should only contain certain types of entities, they go here. If not, leave it empty.
 */
case class Folder(guid: Guid[Folder], parentGuid: Option[Guid[Folder]], entityTypes: Set[EntityType], name: String)

/**
 * Locates an entity within a folder.
 */
case class EntityInFolder[E](guid: Guid[EntityInFolder[E]], folderGuid: Guid[Folder], entityGuid: Guid[E])
