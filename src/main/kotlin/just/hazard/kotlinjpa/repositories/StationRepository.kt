package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Station
import org.springframework.data.jpa.repository.JpaRepository

interface StationRepository : JpaRepository<Station, Long> {

    fun findByName(station: String) : Station
}
