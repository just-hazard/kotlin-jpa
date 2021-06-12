package just.hazard.kotlinjpa.domain

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Member(
    var age: Int,
    @Column(unique = true)
    var email: String,
    var password: String
) : Base()
