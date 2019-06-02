package main.DAO

import org.jetbrains.exposed.dao.IntIdTable

object GenreTable : IntIdTable() {
    val name = varchar("name", 50).uniqueIndex()
}