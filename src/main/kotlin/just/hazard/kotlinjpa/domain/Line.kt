package just.hazard.kotlinjpa.domain

import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class Line (
        var color : String,

        @Column(unique = true)
        var name : String
        ) : Base() {

        }
