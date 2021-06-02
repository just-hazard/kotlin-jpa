package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Favorite
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteRepository : JpaRepository<Favorite, Long> {
}
