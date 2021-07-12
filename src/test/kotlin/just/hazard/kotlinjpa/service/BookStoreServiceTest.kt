package just.hazard.kotlinjpa.service

import just.hazard.kotlinjpa.domain.bookstore.Book
import just.hazard.kotlinjpa.repositories.BookRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookStoreServiceTest {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var bookService: BookStoreService

    @BeforeEach
    fun setUp() {
        bookRepository.saveAll(listOf(
            Book("저스디스",1,1,1),
            Book("저스디스",2,1,1),
            Book("저스디스",3,1,1)))
    }

    @Test
    fun 이름별_책_정렬() {
        val actual = bookService.findBookNameSorting()
        assertThat(actual.size).isEqualTo(1)
    }
}
