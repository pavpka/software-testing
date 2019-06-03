package main

import main.services.Adding
import main.services.Deletion
import main.services.Search

fun main() {
    val dbm = DBManager()
    val searching = Search(dbm)
    val adding = Adding(dbm)
    val deletion = Deletion(dbm)
    var isWork = true
    while (isWork) {
        println(
            "\nВыберите действие:\n1. Поиск по композициям.\n2. Получить информацию обо всех композициях в каталоге.\n" +
                    "3. Добавить новую композицию в каталог\n4. Удалить композицию из каталога\n5. Выйти"
        )
        val id = readLine()
        when (id) {
            "1" -> {
                var repeat = true
                while (repeat) {
                    println("Введите название композиции:")
                    val name = readLine()
                    if (name != "") {
                        println("Введите автора, либо нажмите enter, если хотите найти все композиции с таким названием")
                        val author = readLine()
                        if (author == "") {
                            searching.findCompositionByName(name!!)
                            break
                        } else {
                            searching.findCompositionByNameAndAuthor(name!!, author!!)
                            break
                        }
                    }
                    println("Некорректный ввод.")
                }
            }
            "2" -> {
                val album = readLine()
                searching.findCompositionByAlbum(album!!)}
            "3" -> println("add")
            "4" -> println("delete")
            "5" -> isWork = false
            else -> {
                println("Некорректные данные")
            }
        }
    }
}



