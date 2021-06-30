package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = [UniqueConstraint(name = "linestation_uinque", columnNames = ["line_id","previous_station_id","station_id"])])
class LineStation(
    @ManyToOne(fetch = FetchType.LAZY)
    val line: Line,
    @ManyToOne(fetch = FetchType.LAZY)
    val previousStation: Station,
    @ManyToOne(fetch = FetchType.LAZY)
    val station: Station,
    val distance: Int
) : Base() {
}
