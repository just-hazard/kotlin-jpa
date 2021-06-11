package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}
