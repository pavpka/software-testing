package test

import main.services.Adding
import main.services.Search
import org.junit.Test
import kotlin.test.assertEquals

class AddingTest{
    private val service = Adding()
    private val check = Search()

    @Test
    fun `add new composition`(){
        val name = "newComposition"
        val author = "test1"
        val genre = "test1"
        val album = "test1"
        service.addNewComposition(name, author, genre, album)
        assertEquals(true, check.findCompositionByNameAndAuthor(name, author))
    }

    @Test
    fun `add existing composition`(){
        val name = "test"
        val author = "test"
        val genre = "test"
        val album = "test"
        assertEquals(false, service.addNewComposition(name, author, genre, album))
    }

    @Test
    fun `add new author`(){
        val name = "testAuthor"
        service.addNewAuthor(name)
        assertEquals(true, check.findCompositionByAuthor(name))
    }

    @Test
    fun `add existing author`(){
        val name = "test"
        assertEquals(false, service.addNewAuthor(name))
    }

    @Test
    fun `add new genre`(){
        val name = "newGenre"
        service.addNewGenre(name)
        assertEquals(true, check.findCompositionByGenre(name))
    }

    @Test
    fun `add existing genre`(){
        val name = "test"
        assertEquals(false, service.addNewGenre(name))
    }

    @Test
    fun `add new album`(){
        val name = "newAlbum"
        service.addNewAlbum(name)
        assertEquals(true, check.findCompositionByAlbum(name))
    }

    @Test
    fun `add existing album`(){
        val name = "test"
        assertEquals(false, service.addNewAlbum(name))
    }

}