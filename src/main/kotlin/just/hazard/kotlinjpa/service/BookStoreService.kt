package just.hazard.kotlinjpa.service

import just.hazard.kotlinjpa.domain.bookstore.Book
import just.hazard.kotlinjpa.repositories.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional
class BookStoreService(private val bookRepository: BookRepository) {

    fun insertBook(requestBook: Book.RequestBook): Book.ResponseBook {
        return Book.moveDataResponseBook(bookRepository.save(Book.moveDataBook(requestBook)))
    }

    @Transactional(readOnly = true)
    fun findSortedBookName() : Map<String,MutableList<Book.ResponseBook>> {
        val bookMap: MutableMap<String,MutableList<Book.ResponseBook>> = HashMap()
        val books = bookRepository.findAll()
        books.forEach {
            if(bookMap.containsKey(it.bookName))
                bookMap[it.bookName]!!.add(Book.moveDataResponseBook(it))
            else
                bookMap[it.bookName] = mutableListOf(Book.moveDataResponseBook(it))
        }
        return bookMap
    }

    fun changeBook(id: Long, requestBook: Book.RequestBook): Book.ResponseBook {
        val persistBook = bookRepository.findById(id).orElseThrow { EntityNotFoundException("존재하지 않는 책입니다.") }
        updateBookParameters(persistBook, requestBook)
        return Book.moveDataResponseBook(persistBook)
    }

    fun deleteBook(id: Long) {
        bookRepository.deleteById(id)
    }

    private fun updateBookParameters(
        persistBook: Book,
        requestBook: Book.RequestBook,
    ) {
        persistBook.bookName = requestBook.bookName
        persistBook.bookCase = requestBook.bookCase
        persistBook.bookCaseHeight = requestBook.bookCaseHeight
        persistBook.bookCaseWidth = requestBook.bookCaseWidth
    }
}


