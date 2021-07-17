package just.hazard.kotlinjpa.domain.bookstore

import just.hazard.kotlinjpa.domain.Base
import javax.persistence.Entity

@Entity
class Book(
    var bookName: String,
    var bookCase: Int,
    var bookCaseHeight: Int,
    var bookCaseWidth: Int
) : Base() {

    companion object {
        fun moveResponseObject(book: Book): ResponseBook {
            return ResponseBook(book.bookName,
                book.bookCase,
                book.bookCaseHeight,
                book.bookCaseWidth)
        }
    }

    data class RequestBook(
        val bookName: String,
        val bookCase: Int,
        val bookCaseHeight: Int,
        val bookCaseWidth: Int
    )

    data class ResponseBook(
        val bookName: String,
        val bookCase: Int,
        val bookCaseHeight: Int,
        val bookCaseWidth: Int
    )
}
