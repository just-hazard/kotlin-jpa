package just.hazard.kotlinjpa.domain.bookstore

import just.hazard.kotlinjpa.domain.Base
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Book(
    val bookName: String,
    val bookCase: Int,
    val bookCaseHeight: Int,
    val bookCaseWidth: Int
) : Base() {

}
