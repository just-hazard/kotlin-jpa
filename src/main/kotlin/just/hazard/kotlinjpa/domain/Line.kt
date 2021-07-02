package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class Line (
        var color : String,
        @Column(unique = true)
        var name : String,
        @OneToMany(mappedBy = "line", cascade = [CascadeType.PERSIST])
        val lines: MutableList<LineStation> = mutableListOf()
        ) : Base() {
                fun updateLineStation(lineStation: LineStation) {
                        lines.add(lineStation)
                }
        }
