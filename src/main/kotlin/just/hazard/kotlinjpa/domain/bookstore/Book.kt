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

}
