package just.hazard.kotlinjpa.domain

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Station(
    @Column(unique = true)
    var name: String,
    @OneToMany(mappedBy = "station")
    val stations: MutableList<LineStation> = mutableListOf()
) : Base()
