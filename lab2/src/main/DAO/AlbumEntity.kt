package main.DAO

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class AlbumEntity (id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<AlbumEntity>(AlbumTable)

    var name by AlbumTable.name
}