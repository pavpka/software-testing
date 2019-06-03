package test

import main.services.Deletion
import main.services.Search
import org.junit.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.function.Executable

class DeletionTest {
    private val service = Deletion()
    private val check = Search()

    @Test
    fun `delete composition by existing name`() {
        val name = "newComposition"
        assertAll("composition",
                Executable { assertEquals(true, service.deleteCompositionsByName(name)) },
                Executable { assertEquals(false, check.findCompositionByName(name)) }
        )
    }

    @Test
    fun `delete composition by not existing name`() {
        val name = "abc"
        assertEquals(false, service.deleteCompositionsByName(name))
    }

    @Test
    fun `delete author by existing name`() {
        val name = "newAuthor"
        assertAll("author",
                Executable { assertEquals(true, service.deleteAuthor(name)) },
                Executable { assertEquals(false, check.findAuthorByName(name)) }
        )
    }

    @Test
    fun `delete author by not existing name`() {
        val name = "abc"
        assertEquals(false, service.deleteAuthor(name))
    }

    @Test
    fun `delete genre by existing name`() {
        val name = "newGenre"
        assertAll("genre",
                Executable { assertEquals(true, service.deleteGenre(name)) },
                Executable { assertEquals(false, check.findGenreByName(name)) }
        )
    }

    @Test
    fun `delete genre by not existing name`() {
        val name = "abc"
        assertEquals(false, service.deleteGenre(name))
    }

    @Test
    fun `delete album by existing name`() {
        val name = "newAlbum"
        assertAll("album",
                Executable { assertEquals(true, service.deleteAlbum(name)) },
                Executable { assertEquals(false, check.findAlbumByName(name)) }
        )
    }

    @Test
    fun `delete album by not existing name`() {
        val name = "abc"
        assertEquals(false, service.deleteAlbum(name))
    }


    @Test
    fun `delete composition by existing name and not existing author`() {
        val name = "test"
        val author = "abc"
        assertEquals(false, service.deleteCompositionByNameAndAuthor(name, author))
    }

    @Test
    fun `delete composition by not existing name and not existing author`() {
        val name = "abc"
        val author = "abc"
        assertEquals(false, service.deleteCompositionByNameAndAuthor(name, author))
    }

}