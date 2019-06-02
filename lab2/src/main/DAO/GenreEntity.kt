package main.DAO

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class GenreEntity (id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<GenreEntity>(GenreTable)

    var name by GenreTable.name
}