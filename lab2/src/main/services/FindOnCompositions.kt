package main.services

import main.DBManager

class FindOnCompositions {
    private val dbm = DBManager()

    fun findByName(name: String): Boolean {
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

    fun findByNameAndAuthor(name: String, author: String): Boolean {
        val compositions = dbm.findCompositionsByNameAndAuthor(name, author)
        if (compositions != null) {
            println("${compositions.name} | ${compositions.author.name} | Жанр: ${compositions.genre.name} | Альбом: ${compositions.album.name}")
            return true
        }
        println("Композиция не найдена")
        return false
    }
}
