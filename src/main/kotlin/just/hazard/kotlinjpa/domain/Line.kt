package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class Line (
        var color : String,
        @Column(unique = true)
        var name : String,
        @OneToMany(mappedBy = "line")
        val lines: MutableList<LineStation> = mutableListOf()
        ) : Base() {

        }
