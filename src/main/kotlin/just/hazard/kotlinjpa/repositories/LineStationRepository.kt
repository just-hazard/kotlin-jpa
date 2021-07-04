package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.LineStation
import org.springframework.data.jpa.repository.JpaRepository

interface LineStationRepository: JpaRepository<LineStation, Long> {
    fun findByLineId(lindId: Long): MutableList<LineStation>
}
