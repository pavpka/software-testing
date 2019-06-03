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
import org.junit.jupiter.api.extension.Extension
import javax.transaction.TransactionRequiredException

class DBManager {
    private val userDB: String = System.getenv("DB_USER") ?: ""
    private val passwordDB: String = System.getenv("DB_PASSWORD") ?: ""

    init {
        Database.connect(
            "jdbc:postgresql://localhost:5432/tpo", driver = "org.postgresql.Driver",
            user = userDB, password = passwordDB
        )
        transaction {
            SchemaUtils.drop(CompositionTable)
            SchemaUtils.drop(AlbumTable)
            SchemaUtils.drop(AuthorTable)
            SchemaUtils.drop(GenreTable)

            SchemaUtils.create(AlbumTable)
            SchemaUtils.create(AuthorTable)
            SchemaUtils.create(GenreTable)
            SchemaUtils.create(CompositionTable)

            addComposition("test", "test", "test", "test")
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
            val authorName = AuthorEntity.find { AuthorTable.name eq author }.firstOrNull()?.name ?: return@transaction
            result = CompositionEntity.find { CompositionTable.name eq name }
                .with(CompositionEntity::author, CompositionEntity::genre, CompositionEntity::album)
                .firstOrNull { it.author.name == authorName }
                ?.let { Composition(it.name, Author(it.author.name), Genre(it.genre.name), Album(it.album.name)) }
        }
        return result
    }

    fun findCompositionsByAlbum(albumName: String): List<Composition> {
        var result = emptyList<Composition>()
        transaction {
            val album = AlbumEntity.find { AlbumTable.name eq albumName }.firstOrNull()?.id ?: return@transaction
            result = CompositionEntity.find { CompositionTable.album eq album }.sortedBy { it.author.name }
                .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name), Album(it.album.name)) }
        }
        return result
    }

    fun findCompositionsByAuthor(authorName: String): List<Composition> {
        var result = emptyList<Composition>()
        transaction {
            val author = AuthorEntity.find { AuthorTable.name eq authorName }.firstOrNull()?.id ?: return@transaction
            result = CompositionEntity.find { CompositionTable.author eq author }.sortedBy { it.album.name }
                .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name), Album(it.album.name)) }
        }
        return result
    }

    fun findCompositionsByGenre(genreName: String): List<Composition> {
        var result = emptyList<Composition>()
        transaction {
            val genre = GenreEntity.find { GenreTable.name eq genreName }.firstOrNull()?.id ?: return@transaction
            result = CompositionEntity.find { CompositionTable.genre eq genre }.sortedBy { it.genre.name }
                .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name), Album(it.album.name)) }
        }
        return result
    }

    fun findAllCompositions(): List<Composition> {
        var result = emptyList<Composition>()
        transaction {
            result = CompositionEntity.all().sortedBy { it.author.name }
                .map { Composition(it.name, Author(it.author.name), Genre(it.genre.name), Album(it.album.name)) }
        }
        return result
    }

    fun findAllAuthors(): List<Author> {
        var result = emptyList<Author>()
        transaction {
            result = AuthorEntity.all().map { Author(it.name) }.sortedBy { it.name }
        }
        return result
    }

    fun findAllGenres(): List<Genre> {
        var result = emptyList<Genre>()
        transaction {
            result = GenreEntity.all().map { Genre(it.name) }.sortedBy { it.name }
        }
        return result
    }

    fun findAllAlbums(): List<Album> {
        var result = emptyList<Album>()
        transaction {
            result = AlbumEntity.all().map { Album(it.name) }.sortedBy { it.name }
        }
        return result
    }

    fun findAuthor(name: String): String? {
        var author: String? = null
        transaction {
            author = AuthorEntity.find { AuthorTable.name eq name }.firstOrNull()?.name
        }
        return author
    }

    fun findGenre(name: String): String? {
        var genre: String? = null
        transaction {
            genre = GenreEntity.find { GenreTable.name eq name }.firstOrNull()?.name
        }
        return genre
    }

    fun findAlbum(name: String): String? {
        var album: String? = null
        transaction {
            album = AlbumEntity.find { AlbumTable.name eq name }.firstOrNull()?.name
        }
        return album
    }

    fun addComposition(
        compositionName: String,
        authorName: String,
        genreName: String,
        albumName: String
    ): Composition? {
        var composition: Composition? = null
        transaction {
            val author = AuthorEntity.find { AuthorTable.name eq authorName }.firstOrNull() ?: AuthorEntity.new {
                name = authorName
            }
            val genre = GenreEntity.find { GenreTable.name eq genreName }.firstOrNull() ?: GenreEntity.new {
                name = genreName
            }
            val album = AlbumEntity.find { AlbumTable.name eq albumName }.firstOrNull() ?: AlbumEntity.new {
                name = albumName
            }
            if (findCompositionsByNameAndAuthor(compositionName, authorName) != null) {
                return@transaction
            } else {
                val newComposition = CompositionEntity.new {
                    name = compositionName
                    this.author = author
                    this.genre = genre
                    this.album = album
                }
                composition =
                    Composition(newComposition.name, Author(author.name), Genre(genre.name), Album(album.name))
            }
        }
        return composition
    }

    fun addAuthor(authorName: String): Author? {
        var author: Author? = null
        transaction {

            val empty = AuthorEntity.find { AuthorTable.name eq authorName }.empty()
            if (!empty) {
                return@transaction
            }
            val newAuthor = AuthorEntity.new {
                this.name = authorName
            }
            author = Author(newAuthor.name)

        }
        return author
    }

    fun addGenre(genreName: String): Genre? {
        var genre: Genre? = null
        transaction {

            val empty = GenreEntity.find { GenreTable.name eq genreName }.empty()
            if (!empty) {
                return@transaction
            }
            val newGenre = GenreEntity.new {
                this.name = genreName
            }
            genre = Genre(newGenre.name)

        }
        return genre
    }

    fun addAlbum(albumName: String): Album? {
        var album: Album? = null
        transaction {

            val empty = AlbumEntity.find { AlbumTable.name eq albumName }.empty()
            if (!empty) {
                return@transaction
            }
            val newAlbum = AlbumEntity.new {
                this.name = albumName
            }
            album = Album(newAlbum.name)

        }
        return album
    }

    fun deleteCompositionByName(name: String): Boolean {
        return when (findCompositionsByName(name).isNotEmpty()) {
            true -> {
                transaction {
                    val compositions = CompositionEntity.find { CompositionTable.name eq name }
                    compositions.forEach {
                        it.delete()
                    }
                }
                true
            }
            false -> false
        }
    }

    fun deleteCompositionByNameAndAuthor(name: String, author: String): Boolean {
        return when (findCompositionsByNameAndAuthor(name, author) != null) {
            true -> {
                transaction {
                    CompositionEntity.find { CompositionTable.name eq name }
                        .firstOrNull { it.author.name == author }?.delete()
                }
                true
            }
            false -> false
        }
    }

    fun deleteAuthor(name: String): Boolean {
        return when (findAuthor(name) != null) {
            true -> {
                transaction {
                    AuthorEntity.find { AuthorTable.name eq name }
                        .firstOrNull()?.delete()
                }
                true
            }
            false -> false
        }
    }

    fun deleteGenre(name: String): Boolean {
        return when (findGenre(name) != null) {
            true -> {
                transaction {
                    GenreEntity.find { GenreTable.name eq name }
                        .firstOrNull()?.delete()
                }
                true
            }
            false -> false
        }
    }

    fun deleteAlbum(name: String): Boolean {
        return when (findAlbum(name) != null) {
            true -> {
                transaction {
                    AlbumEntity.find { AlbumTable.name eq name }
                        .firstOrNull()?.delete()
                }
                true
            }
            false -> false
        }
    }
}

