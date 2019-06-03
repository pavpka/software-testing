package main.services

import main.DBManager

class Search(private val dbm: DBManager) {

    fun findCompositionByName(name: String): Boolean {
        val compositions = dbm.findCompositionsByName(name)
        if (compositions.isNotEmpty()) {
            compositions.forEach {
                println("${it.name} | ${it.author.name} | Жанр: ${it.genre.name} | Альбом: ${it.album.name}")
                return true
            }
        }
        println("Композиция не найдена")
        return false
    }

    fun findCompositionByNameAndAuthor(name: String, author: String): Boolean {
        val compositions = dbm.findCompositionsByNameAndAuthor(name, author)
        if (compositions != null) {
            println("${compositions.name} | ${compositions.author.name} | Жанр: ${compositions.genre.name} | Альбом: ${compositions.album.name}")
            return true
        }
        println("Композиция не найдена")
        return false
    }

    fun findCompositionByAlbum(album: String): Boolean {
        val compositions = dbm.findCompositionsByAlbum(album)
        if (compositions.isNotEmpty()) {
            compositions.forEach {
                println("${it.name} | ${it.author.name} | Жанр: ${it.genre.name}")
                return true
            }
        }
        println("Композиции в альбоме $album не найдены")
        return false
    }

    fun findCompositionByAuthor(author: String): Boolean {
        val compositions = dbm.findCompositionsByAuthor(author)
        if (compositions.isNotEmpty()) {
            compositions.forEach {
                println("${it.name} | Жанр: ${it.genre.name} | Альбом: ${it.album.name}")
                return true
            }
        }
        println("Композиции автора $author не найдены")
        return false
    }

    fun findCompositionByGenre(genre: String): Boolean {
        val compositions = dbm.findCompositionsByGenre(genre)
        if (compositions.isNotEmpty()) {
            compositions.forEach {
                println("${it.name} | ${it.author.name} | Альбом: ${it.album.name}")
                return true
            }
        }
        println("Композиции жанра $genre не найдены")
        return false
    }

    fun findAllCompositions(): Boolean {
        val compositions = dbm.findAllCompositions()
        println(compositions.size)
        if (compositions.isNotEmpty()) {
            compositions.forEach {
                println("${it.name} | Автор: ${it.author.name} | Жанр: ${it.genre.name} | Альбом: ${it.album.name}")
            }
            return true
        }
        println("В каталоге не найдено композиций")
        return false
    }

    fun findAllAuthors(): Boolean {
        val authors = dbm.findAllAuthors()
        if (authors.isNotEmpty()) {
            authors.forEach {
                println(it.name)
                return true
            }
        }
        println("В каталоге не найдено ни одного автора")
        return false
    }

    fun findAllGenres(): Boolean {
        val genres = dbm.findAllGenres()
        if (genres.isNotEmpty()) {
            genres.forEach {
                println(it.name)
                return true
            }
        }
        println("В каталоге не найдено ни одного жанра")
        return false
    }

    fun findAllAlbums(): Boolean {
        val albums = dbm.findAllAlbums()
        if (albums.isNotEmpty()) {
            albums.forEach {
                println(it.name)
                return true
            }
        }
        println("В каталоге не найдено ни одного альбома")
        return false
    }

    fun findAuthor(name: String): String? {
        return dbm.findAuthor(name)
    }

    fun findGenre(name: String): String? {
        return dbm.findGenre(name)
    }

    fun findAlbum(name: String): String? {
        return dbm.findAlbum(name)
    }

}
