package main

fun main() {
    val dbm = DBManager()
    val app = UI(dbm)

    app.start()
}



