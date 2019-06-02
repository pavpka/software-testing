package main.DAO

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class AuthorEntity (id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<AuthorEntity>(AuthorTable)

    var name by AuthorTable.name
}