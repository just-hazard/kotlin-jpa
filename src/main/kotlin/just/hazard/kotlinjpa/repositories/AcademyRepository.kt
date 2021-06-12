package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Academy
import org.springframework.data.jpa.repository.JpaRepository

interface AcademyRepository : JpaRepository<Academy, Long> {
}
