package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.example.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<Subject, Long> {
}
