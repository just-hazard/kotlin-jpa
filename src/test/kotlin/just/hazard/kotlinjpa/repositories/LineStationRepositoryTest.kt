package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Distance
import just.hazard.kotlinjpa.domain.Line
import just.hazard.kotlinjpa.domain.LineStation
import just.hazard.kotlinjpa.domain.Station
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
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

    val 칠호선 = Line("연두색","7호선")
    val 수락산역 = Station("수락산역")
    val 건대입구역 = Station("건대입구역")
    val 청담역 = Station("청담역")

    @BeforeEach
    fun setUp() {
        lineStationRepository.save(LineStation(칠호선,수락산역,건대입구역, Distance(10)))
    }

    @AfterEach
    fun tearDown() {
        lineStationRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table line_station alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 유니크키_중복체크_라인_이전역_현재역() {
        assertThrows<DataIntegrityViolationException> {
            lineStationRepository.save(LineStation(lineRepository.findByName("7호선"),
                stationRepository.findByName("수락산역"),
                stationRepository.findByName("건대입구역"), Distance(15)))
        }
        entityManager.clear()
    }

    @Test
    fun 그래프_탐색() {
        // given
        lineStationRepository.save(LineStation(칠호선,건대입구역,청담역, Distance(15)))
        // when
        val lineStations = lineStationRepository.findByLineId(1L)
        // then
        assertThat(lineStations[0].station.name).isEqualTo("건대입구역")
        assertThat(lineStations[1].station.name).isEqualTo("청담역")
    }
}
