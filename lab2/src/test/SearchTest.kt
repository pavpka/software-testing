import main.services.Search
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SearchTest {

    private val service = Search()

    @Test
    fun `find compositions by existing name`() {
        val name = "test"
        assertEquals(true, service.findCompositionByName(name))
    }

    @Test
    fun `find composition by not existing name`() {
        val name = "abc"
        assertEquals(false, service.findCompositionByName(name))
    }

    @Test
    fun `find composition by existing name and existing author`(){
        val name = "test"
        val author = "test"
        assertEquals(true, service.findCompositionByNameAndAuthor(name, author))
    }

    @Test
    fun `find composition by existing name and not existing author`(){
        val name = "test"
        val author = "abc"
        assertEquals(false, service.findCompositionByNameAndAuthor(name, author))
    }

    @Test
    fun `find composition by not existing name and not existing author`(){
        val name = "abc"
        val author = "abc"
        assertEquals(false, service.findCompositionByNameAndAuthor(name, author))
    }

    @Test
    fun `find compositions by existing album`(){
        val albumName = "test"
        assertEquals(true, service.findCompositionByAlbum(albumName))
    }

    @Test
    fun `find compositions by not existing album`(){
        val albumName = "abc"
        assertEquals(false, service.findCompositionByAlbum(albumName))
    }

    @Test
    fun `find compositions by existing genre`(){
        val genreName = "test"
        assertEquals(true, service.findCompositionByGenre(genreName))
    }

    @Test
    fun `find compositions by not existing genre`(){
        val genreName = "abc"
        assertEquals(false, service.findCompositionByGenre(genreName))
    }

    @Test
    fun `find all compositions`(){
        assertEquals(true, service.findAllCompositions())
    }

    @Test
    fun `find compositions by existing author`(){
        val authorName = "test"
        assertEquals(true, service.findCompositionByAuthor(authorName))
    }

    @Test
    fun `find compositions by not existing author`(){
        val authorName = "abc"
        assertEquals(false, service.findCompositionByAuthor(authorName))
    }

    @Test
    fun `find all authors`(){
        assertEquals(true, service.findAllAuthors())
    }
    @Test
    fun `find all genres`(){
        assertEquals(true, service.findAllGenres())
    }
    @Test
    fun `find all albums`(){
        assertEquals(true, service.findAllAlbums())
    }
}