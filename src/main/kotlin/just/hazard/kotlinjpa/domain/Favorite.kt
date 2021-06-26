package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Favorite (
    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var departureStation: Station,
    @ManyToOne(cascade = [CascadeType.PERSIST], fetch = FetchType.LAZY)
    var arrivalStation: Station
) : Base() {

}
