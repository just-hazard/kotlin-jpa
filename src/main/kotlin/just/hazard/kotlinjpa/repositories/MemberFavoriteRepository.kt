package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.MemberFavorite
import org.springframework.data.jpa.repository.JpaRepository

interface MemberFavoriteRepository : JpaRepository<MemberFavorite, Long> {
}
