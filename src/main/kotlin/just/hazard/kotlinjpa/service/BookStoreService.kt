package just.hazard.kotlinjpa.service

import just.hazard.kotlinjpa.domain.bookstore.Book
import just.hazard.kotlinjpa.repositories.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookStoreService(private val bookRepository: BookRepository) {

    @Transactional(readOnly = true)
    fun findBookNameSorting() : Map<String,List<Book>> {
        val bookMap: MutableMap<String,List<Book>> = HashMap()

        val books = bookRepository.findAll()
        // 책 조회 후 빠른 탐색을 위해 책 이름을 기준으로 List를 Map에 담기
        books.stream()
            .map {
                bookMap.merge(
                    it.bookName,
                    mutableListOf(it),

                )
            }

        return mutableMapOf()
    }
}
