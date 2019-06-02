package main

import main.DAO.*
import main.data.Album
import main.data.Author
import main.data.Composition
import main.data.Genre
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class DBManager {
    private val userDB: String = System.getenv("DB_USER") ?: ""
    private val passwordDB: String = System.getenv("DB_PASSWORD") ?: ""

    init {
        Database.connect(
            "jdbc:postgresql://localhost:5432/tpo", driver = "org.postgresql.Driver",
            user = userDB, password = passwordDB
        )
        transaction {
            SchemaUtils.create(AlbumTable)
            SchemaUtils.create(AuthorTable)
            SchemaUtils.create(GenreTable)
            SchemaUtils.create(CompositionTable)
        }
    }

    fun findCompositionsByName(name: String): List<Composition> {
        var result = emptyList<Composition>()
        transaction {
            result = CompositionEntity.find { CompositionTable.name eq name }
                .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name), Album(it.album.name)) }
        }
        return result
    }

    fun findCompositionsByNameAndAuthor(name: String, author: String): Composition? {
        var result: Composition? = null
        transaction {
            val authorName = AuthorEntity.find{AuthorTable.name eq author}.firstOrNull()?.name ?: return@transaction
            result = CompositionEntity.find { CompositionTable.name eq name }
                .with(CompositionEntity::author, CompositionEntity::genre, CompositionEntity::album).firstOrNull{it.author.name == authorName}
                ?.let { Composition(it.name, Author(it.author.name), Genre(it.genre.name), Album(it.album.name)) }
        }
        return result
    }
}

