package just.hazard.kotlinjpa.domain

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Station(
    @Column(unique = true)
    var name: String
) : Base()
