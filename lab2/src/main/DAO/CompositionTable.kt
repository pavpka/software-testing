package main.DAO

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object CompositionTable : IntIdTable() {

    val name = varchar("name", 50).uniqueIndex()
    val author = reference("author", AuthorTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val genre = reference("genre", GenreTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val album = reference("album", AlbumTable, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
}