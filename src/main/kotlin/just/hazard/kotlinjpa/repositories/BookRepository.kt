package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.bookstore.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {
}
