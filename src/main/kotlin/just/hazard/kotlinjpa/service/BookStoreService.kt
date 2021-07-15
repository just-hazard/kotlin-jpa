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
            if(bookMap.containsKey(it.bookName))
                bookMap[it.bookName]!!.add(it)
            else
                bookMap[it.bookName] = mutableListOf(it)
        }
        return bookMap
    }
}


