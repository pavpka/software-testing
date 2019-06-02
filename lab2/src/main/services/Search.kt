package main.services

import main.DBManager

class Search {
    private val dbm = DBManager()

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
        return false
    }

    fun findCompositionByAuthor(author: String): Boolean {
        return false
    }

    fun findCompositionByGenre(genre: String): Boolean {
        return false
    }

    fun findAllCompositions(): Boolean {
        return false
    }

    fun findAuthorByName(name: String): Boolean {
        return false
    }

    fun findGenreByName(name: String): Boolean {
        return false
    }

    fun findAlbumByName(name: String): Boolean {
        return false
    }

}
