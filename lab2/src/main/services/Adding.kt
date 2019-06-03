package main.services

import main.DBManager

class Adding(private val dbm: DBManager) {
    fun addNewComposition(name: String, author: String, genre: String, album: String): Boolean {
        val composition = dbm.addComposition(name, author, genre, album)
        if (composition != null) {
            println("Композиция $name добавлена")
            return true
        }
        println("Композиция не добавлена")
        return false
    }

    fun addNewAuthor(name: String): Boolean {
        val author = dbm.addAuthor(name)
        if (author != null) {
            println("Автор $name добавлен")
            return true
        }
        println("Автор не добавлен")
        return false
    }

    fun addNewGenre(name: String): Boolean {
        val genre = dbm.addGenre(name)
        if (genre != null) {
            println("Жанр $name добавлен")
            return true
        }
        println("Жанр не добавлен")
        return false
    }

    fun addNewAlbum(name: String): Boolean {
        val album = dbm.addAlbum(name)
        if (album != null) {
            println("Альбом $name добавлен")
            return true
        }
        println("Альбом не добавлен")
        return false
    }

}