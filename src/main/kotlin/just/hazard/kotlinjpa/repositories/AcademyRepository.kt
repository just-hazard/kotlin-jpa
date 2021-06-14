package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Academy
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AcademyRepository : JpaRepository<Academy, Long> {

    @Query(value = "select a from Academy a join fetch a.subjects")
    fun findAllFetchJoin() : MutableSet<Academy>

    @EntityGraph(attributePaths = ["subjects"])
    @Query(value = "select DISTINCT a from Academy a")
    fun findAllEntityGraph() : MutableList<Academy>
}
