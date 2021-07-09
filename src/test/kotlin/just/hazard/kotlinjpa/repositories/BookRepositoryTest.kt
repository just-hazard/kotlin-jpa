package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.bookstore.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var book: Book

    @BeforeEach
    fun setUp() {
        book = Book("사수가 없어도 괜찮습니다.",3,1,1)
    }

    @AfterEach
    fun tearDown() {
        bookRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table book alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 생성() {
        val actual = 책_저장()
        책_데이터_체크(actual, "사수가 없어도 괜찮습니다.",3,1,1)
    }

    @Test
    fun 읽기() {
        책_저장()
        val actual = bookRepository.findById(1L).get()
        책_데이터_체크(actual, "사수가 없어도 괜찮습니다.",3,1,1)
    }

    @Test
    fun 수정() {
        val beforeModifications = 책_저장()
        beforeModifications.bookName = "책제목"
        beforeModifications.bookCase = 4
        beforeModifications.bookCaseHeight = 2
        beforeModifications.bookCaseWidth = 2
        val actual = bookRepository.findByBookName("책제목")
        assertThat(beforeModifications == actual).isTrue
        actual?.let { 책_데이터_체크(it,"책제목",4,2,2) }
    }

    @Test
    fun 삭제() {
        책_저장()
        bookRepository.delete(book)
        val actual = bookRepository.findByBookName("사수가 없어도 괜찮습니다.")
        assertThat(actual).isNull()
    }

    private fun 책_저장() = bookRepository.save(book)

    private fun 책_데이터_체크(actual: Book, bookName: String, bookCase: Int, bookCaseHeight: Int, bookCaseWidth: Int) {
        assertAll(
            { assertThat(actual.bookName).isEqualTo(bookName) },
            { assertThat(actual.bookCase).isEqualTo(bookCase) },
            { assertThat(actual.bookCaseHeight).isEqualTo(bookCaseHeight) },
            { assertThat(actual.bookCaseWidth).isEqualTo(bookCaseWidth) },
            { assertThat(actual.id).isNotNull() },
            { assertThat(actual.createdDate).isNotNull() },
            { assertThat(actual.modifiedDate).isNotNull() },
        )
    }


}
