package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = [UniqueConstraint(name = "station_uinque", columnNames = ["departure_station_id","arrival_station_id"])])
class Favorite (
    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var departureStation: Station,
    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var arrivalStation: Station
) : Base() {

}
