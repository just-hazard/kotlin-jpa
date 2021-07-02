package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = [UniqueConstraint(name = "linestation_uinque", columnNames = ["line_id","previous_station_id","station_id"])])
class LineStation(
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val line: Line,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val previousStation: Station,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val station: Station,
    @Embedded
    var distance: Distance
) : Base() {

}
