package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Line
import org.springframework.data.jpa.repository.JpaRepository

interface LineRepository : JpaRepository<Line, Long> {
}
