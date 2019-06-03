package main

import main.services.Adding
import main.services.Deletion
import main.services.Search

class UI(private val dbm: DBManager) {

    fun start() {
        val searching = Search(dbm)
        val adding = Adding(dbm)
        val deletion = Deletion(dbm)
        var isWork = true
        while (isWork) {
            println(
                "\nПриложение \"Музыкальный каталог\"\nДля перехода какой-либо раздел каталога введите соответствующую команду:\n" +
                        "1. Поиск по композициям.\n2. Получить информацию.\n" +
                        "3. Добавить в каталог\n4. Удалить из каталога\n5. Выйти"
            )
            val id = readLine()
            when (id) {
                "1" -> search(searching)
                "2" -> getAll(searching)
                "3" -> addNew(adding)
                "4" -> deleteSmth(deletion)
                "5" -> isWork = false
                else -> {
                    println("Некорректный ввод")
                }
            }
        }
    }

    fun search(searching: Search) {
        loop@ while (true) {
            println(
                "\n|Поиск среди композиций|\nВыберите действие:\n1. Поиск по названию композиции.\n2. Поиск по имени автора.\n" +
                        "3. Поиск по названию жанра\n4. Поиск по названию альбома\n5. Выйти в главное меню"
            )
            val id = readLine()
            when (id) {
                "1" -> {
                    println("Введите название композиции:")
                    val name = readLine()
                    if (!name.isNullOrEmpty()) {
                        println("Введите автора, либо нажмите enter, если хотите найти все композиции с таким названием")
                        val author = readLine()
                        if (author.isNullOrEmpty()) {
                            searching.findCompositionByName(name)
                            break@loop
                        } else {
                            searching.findCompositionByNameAndAuthor(name, author)
                            break@loop
                        }
                    }
                    println("Некорректный ввод.")
                }
                "2" -> {
                    println("Введите имя автора:")
                    val author = readLine()
                    if (author != "") {
                        searching.findCompositionByAuthor(author!!)
                        break@loop
                    }
                    println("Некорректный ввод.")
                }
                "3" -> {
                    println("Введите название жанра:")
                    val genre = readLine()
                    if (genre != "") {
                        searching.findCompositionByGenre(genre!!)
                        break@loop
                    }
                    println("Некорректный ввод.")
                }
                "4" -> {
                    println("Введите название альбома:")
                    val album = readLine()
                    if (album != "") {
                        searching.findCompositionByGenre(album!!)
                        break@loop
                    }
                    println("Некорректный ввод.")
                }
                "5" -> return
                else -> {
                    println("Некорректный ввод.")
                }
            }

        }
    }

    fun getAll(searching: Search) {
        loop@ while (true) {
            println(
                "\n|Получить информацию|\nВыберите действие:\n1. Получить список всех композиций.\n2. Получить список всех авторов.\n" +
                        "3. Получить список всех жанров\n4. Получить список всех альбомов\n5. Выйти в главное меню"
            )
            val id = readLine()
            when (id) {
                "1" -> searching.findAllCompositions()
                "2" -> searching.findAllAuthors()
                "3" -> searching.findAllGenres()
                "4" -> searching.findAllAlbums()
                "5" -> return
                else -> {
                    println("Некорректный ввод.")
                }
            }
        }
    }

    fun addNew(adding: Adding) {
        loop@ while (true) {
            println(
                "\n|Добавить в каталог|\nВыберите действие:\n1. Добавить новую композицию.\n2. Добавить нового автора.\n" +
                        "3. Добавить новый жанр\n4. Добавить новый альбом\n5. Выйти в главное меню"
            )
            val id = readLine()
            when (id) {
                "1" -> {

                    println("Введите название композиции:\n")
                    val name = readLine()
                    if (name.isNullOrEmpty()) {
                        println("Некорректный ввод.")
                        break@loop
                    }
                    println("Введите автора:\n")
                    val author = readLine()
                    if (author.isNullOrEmpty()) {
                        println("Некорректный ввод.")
                        break@loop
                    }
                    println("Введите название жанра:\n")
                    val genre = readLine()
                    if (genre.isNullOrEmpty()) {
                        println("Некорректный ввод.")
                        break@loop
                    }
                    println("Введите название альбома:\n")
                    var album = readLine()
                    if (album.isNullOrEmpty()) {
                        album = "single"
                    }
                    adding.addNewComposition(name, author, genre, album)
                }
                "2" -> {
                    println("Введите имя автора:\n")
                    val author = readLine()
                    if (author.isNullOrEmpty()) {
                        println("Некорректный ввод.")
                        break@loop
                    }
                    adding.addNewAuthor(author)
                }
                "3" -> {
                    println("Введите название жанра:\n")
                    val genre = readLine()
                    if (genre.isNullOrEmpty()) {
                        println("Некорректный ввод.")
                        break@loop
                    }
                    adding.addNewGenre(genre)
                }
                "4" -> {
                    println("Введите название альбома:\n")
                    val album = readLine()
                    if (album.isNullOrEmpty()) {
                        println("Некорректный ввод.")
                        break@loop
                    }
                    adding.addNewAuthor(album)
                }
                "5" -> return
                else -> {
                    println("Некорректный ввод.")
                }
            }
        }
    }

    fun deleteSmth(deletion: Deletion) {
        loop@ while (true) {
            println(
                "\n|Удалить из каталога|\nВыберите действие:\n1. Удалить композицию.\n2. Удалить автора.\n" +
                        "3. Удалить жанр\n4. Удалить альбом\n5. Выйти в главное меню"
            )
            val id = readLine()
            when (id) {
                "1" -> {
                    println("Введите название композиции:")
                    val name = readLine()
                    if (!name.isNullOrEmpty()) {
                        println("Введите автора, либо нажмите enter, если хотите найти все композиции с таким названием")
                        val author = readLine()
                        if (author.isNullOrEmpty()) {
                            deletion.deleteCompositionsByName(name)
                            break@loop
                        } else {
                            deletion.deleteCompositionByNameAndAuthor(name, author)
                            break@loop
                        }
                    }
                    println("Некорректный ввод.")
                }
                "2" -> {
                    println("Введите имя автора:")
                    val author = readLine()
                    if (!author.isNullOrEmpty()) {
                        deletion.deleteAuthor(author)
                        break@loop
                    }
                    println("Некорректный ввод.")
                }
                "3" -> {
                    println("Введите название жанра:")
                    val genre = readLine()
                    if (!genre.isNullOrEmpty()) {
                        deletion.deleteGenre(genre)
                        break@loop
                    }
                    println("Некорректный ввод.")
                }
                "4" -> {
                    println("Введите название альбома:")
                    val album = readLine()
                    if (!album.isNullOrEmpty()) {
                        deletion.deleteGenre(album)
                        break@loop
                    }
                    println("Некорректный ввод.")
                }
                "5" -> return
                else -> {
                    println("Некорректный ввод.")
                }
            }
        }

    }
}