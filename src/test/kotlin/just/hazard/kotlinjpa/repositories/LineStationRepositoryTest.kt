package just.hazard.kotlinjpa.repositories

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import javax.persistence.EntityManager

@DataJpaTest
class LineStationRepositoryTest {

    @Autowired
    private lateinit var lineStationRepository: LineStationRepository

    @Autowired
    private lateinit var stationRepository: StationRepository

    @Autowired
    private lateinit var lineRepository: LineRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    @BeforeEach
    fun setUp() {

    }

    @AfterEach
    fun tearDown() {
        lineStationRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table line_station alter column id restart 1")
            .executeUpdate()
    }
}
