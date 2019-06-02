package main.DAO


import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class CompositionEntity (id: EntityID<Int>) : IntEntity(id){
    companion object: IntEntityClass<CompositionEntity>(CompositionTable)

    var name by CompositionTable.name
    var author by AuthorEntity referencedOn CompositionTable.author
    var genre by GenreEntity referencedOn CompositionTable.genre
    var album by AlbumEntity referencedOn CompositionTable.album
}