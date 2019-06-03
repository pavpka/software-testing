package test

import main.DBManager
import main.services.Adding
import main.services.Search
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.function.Executable
import kotlin.test.assertEquals

class AddingTest {
    private val dbm = DBManager()
    private val service = Adding(dbm)
    private val check = Search(dbm)

    @Test
    fun `add new composition`() {
        val name = "newComposition"
        val author = "test1"
        val genre = "test1"
        val album = "test1"
        Assertions.assertAll("composition",
            Executable { assertEquals(true, service.addNewComposition(name, author, genre, album)) },
            Executable { assertEquals(true, check.findCompositionByNameAndAuthor(name, author)) }
        )
    }

    @Test
    fun `add existing composition`() {
        val name = "test"
        val author = "test"
        val genre = "test"
        val album = "test"
        assertEquals(false, service.addNewComposition(name, author, genre, album))
    }

    @Test
    fun `add new author`() {
        val name = "testAuthor"
        Assertions.assertAll("author",
            Executable { assertEquals(true, service.addNewAuthor(name)) },
            Executable { assertEquals(name, check.findAuthor(name)) }
        )
    }

    @Test
    fun `add existing author`() {
        val name = "test"
        assertEquals(false, service.addNewAuthor(name))
    }

    @Test
    fun `add new genre`() {
        val name = "newGenre"
        Assertions.assertAll("genre",
            Executable { assertEquals(true, service.addNewGenre(name)) },
            Executable { assertEquals(name, check.findGenre(name)) }
        )
    }

    @Test
    fun `add existing genre`() {
        val name = "test"
        assertEquals(false, service.addNewGenre(name))
    }

    @Test
    fun `add new album`() {
        val name = "newAlbum"
        Assertions.assertAll("album",
            Executable { assertEquals(true, service.addNewAlbum(name)) },
            Executable { assertEquals(name, check.findAlbum(name)) }
        )
    }

    @Test
    fun `add existing album`() {
        val name = "test"
        assertEquals(false, service.addNewAlbum(name))
    }

}