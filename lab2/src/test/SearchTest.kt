import main.services.Search
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Search {

    private val service = Search()

    @Test
    fun `find compositions by existing name`() {
        assertEquals(true, service.findCompositionByName("test"))
    }

    @Test
    fun `find composition by not existing name`() {
        assertEquals(false, service.findCompositionByName("abc"))
    }

    @Test
    fun `find composition by existing name and existing author`(){
        assertEquals(true, service.findCompositionByNameAndAuthor("test", "test"))
    }

    @Test
    fun `find composition by existing name and not existing author`(){
        assertEquals(false, service.findCompositionByNameAndAuthor("test", "abc"))
    }

    @Test
    fun `find composition by not existing name and not existing author`(){
        assertEquals(false, service.findCompositionByNameAndAuthor("abc", "abc"))
    }

    @Test
    fun `find compositions by existing album`(){
        assertEquals(true, service.findCompositionByAlbum("test"))
    }

    @Test
    fun `find compositions by not existing album`(){
        assertEquals(false, service.findCompositionByAlbum("abc"))
    }

    @Test
    fun `find compositions by existing genre`(){
        assertEquals(true, service.findCompositionByGenre("test"))
    }

    @Test
    fun `find compositions by not existing genre`(){
        assertEquals(false, service.findCompositionByGenre("abc"))
    }

    @Test
    fun `find all compositions`(){
        assertEquals(true, service.findAllCompositions())
    }

    @Test
    fun `find compositions by existing author`(){
        assertEquals(true, service.findCompositionByAuthor("test"))
    }

    @Test
    fun `find compositions by not existing author`(){
        assertEquals(false, service.findCompositionByAuthor("abc"))
    }

    @Test
    fun `find album by existing name`(){
        assertEquals(true, service.findAlbumByName("test"))
    }

    @Test
    fun `find album by not existing name`(){
        assertEquals(false, service.findAlbumByName("abc"))
    }

    @Test
    fun `find author by existing name`(){
        assertEquals(true, service.findAuthorByName("test"))
    }

    @Test
    fun `find author by not existing name`(){
        assertEquals(false, service.findAuthorByName("abc"))
    }

    @Test
    fun `find genre by existing name`(){
        assertEquals(true, service.findGenreByName("test"))
    }

    @Test
    fun `find genre by not existing name`(){
        assertEquals(false, service.findGenreByName("test"))
    }
}