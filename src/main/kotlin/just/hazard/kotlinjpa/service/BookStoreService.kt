package just.hazard.kotlinjpa.service

import just.hazard.kotlinjpa.domain.bookstore.Book
import just.hazard.kotlinjpa.repositories.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookStoreService(private val bookRepository: BookRepository) {

    @Transactional(readOnly = true)
    fun findBookNameSorting() : Map<String,MutableList<Book>> {
        val bookMap: MutableMap<String,MutableList<Book>> = HashMap()
        val books = bookRepository.findAll()
        books.forEach {
            bookMap.merge(it.bookName, mutableListOf(it), { _, _ -> )
        }
        // 책 조회 후 빠른 탐색을 위해 책 이름을 기준으로 List를 Map에 담기
//        val unionList = (mapA.asSequence() + mapB.asSequence())
//            .distinct()
//            .groupBy({ it.key }, { it.value })
//            .mapValues { (_, values) -> values.joinToString(",") }
        return bookMap
    }

    fun <K, V> Map<K, V>.mergeReduce(other: Map<K, V>, reduce: (V, V) -> V): Map<K, V> {
        val result = LinkedHashMap<K, V>(this)
        for ((key, value) in other) {
            result.merge(key, value, reduce)
        }
        return result
    }
}


