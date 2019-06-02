package main.DAO

import org.jetbrains.exposed.dao.IntIdTable

object AlbumTable : IntIdTable() {
    val name = varchar("name", 50).uniqueIndex()
}