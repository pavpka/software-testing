package main.services

import main.DBManager

class Deletion(private val dbm: DBManager) {
    fun deleteCompositionsByName(name: String): Boolean {
        return when (dbm.deleteCompositionByName(name)) {
            true -> {
                println("Композиция $name удалена")
                true
            }
            false -> {
                println("Композиция не существует")
                false
            }
        }
    }

    fun deleteCompositionByNameAndAuthor(name: String, author: String): Boolean {
        return when (dbm.deleteCompositionByNameAndAuthor(name, author)) {
            true -> {
                println("Композиция $name автора $author удалена")
                true
            }
            false -> {
                println("Композиция не существует")
                false
            }
        }
    }

    fun deleteAuthor(name: String): Boolean {
        return when (dbm.deleteAuthor(name)) {
            true -> {
                println("Автор $name удален")
                true
            }
            false -> {
                println("Автор не существует")
                false
            }
        }
    }

    fun deleteGenre(name: String): Boolean {
        return when (dbm.deleteGenre(name)) {
            true -> {
                println("Жанр $name удален")
                true
            }
            false -> {
                println("Жанр не существует")
                false
            }
        }
    }

    fun deleteAlbum(name: String): Boolean {
        return when (dbm.deleteAlbum(name)) {
            true -> {
                println("Альбом $name удален")
                true
            }
            false -> {
                println("Альбом не существует")
                false
            }
        }
    }

}