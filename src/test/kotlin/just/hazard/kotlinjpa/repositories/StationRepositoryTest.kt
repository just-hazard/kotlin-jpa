package just.hazard.kotlinjpa.repositories

import just.hazard.kotlinjpa.domain.Distance
import just.hazard.kotlinjpa.domain.Line
import just.hazard.kotlinjpa.domain.LineStation
import just.hazard.kotlinjpa.domain.Station
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.dao.DataIntegrityViolationException
import javax.persistence.EntityManager

@DataJpaTest
class StationRepositoryTest {

    @Autowired
    private lateinit var stationRepository: StationRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var station: Station

    @BeforeEach
    fun setUp() {
        station = stationRepository.save(Station("의정부역"))
    }

    @AfterEach
    fun tearDown() {
        stationRepository.deleteAll()
        entityManager
            .createNativeQuery("alter table station alter column id restart 1")
            .executeUpdate()
    }

    @Test
    fun 저장() {
        지하철역_데이터_체크(station, "의정부역")
    }

    // AfterEach 예외하고 돌릴수 있는지 찾아보기
    @Test
    @Disabled
    fun 지하철역_이름_중복_예외() {
        Assertions.assertThatThrownBy {
            stationRepository.save(Station("의정부역"))
        }.isInstanceOf(DataIntegrityViolationException::class.java)
    }

    @Test
    fun 가져오기() {
        val 의정부역 = 지하철역_조회()
        지하철역_데이터_체크(의정부역, "의정부역")
    }

    @Test
    fun 수정() {
        val 의정부역 = 지하철역_조회()
        의정부역.name = "도봉산역"
        station = stationRepository.save(의정부역)
        지하철역_데이터_체크(station, "도봉산역")
    }

    @Test
    fun 삭제() {
        stationRepository.delete(station)
        Assertions.assertThatThrownBy {
            지하철역_조회()
        }.isInstanceOf(NoSuchElementException::class.java)
    }

    @Test
    fun 지하철역_조회시_그래프탐색() {
        라인_지하철역_등록()
        val actual = stationRepository.findByName("고속터미널역")
        지하철역_데이터_체크(actual,"고속터미널역")
        actual.stations.forEach {
            MatcherAssert.assertThat(it.line.name,
                CoreMatchers.either(CoreMatchers.containsString("7호선")).or(CoreMatchers.containsString("2호선")).or(CoreMatchers.containsString("3호선")))
        }
    }

    private fun 라인_지하철역_등록() {
        val station_expressBusTerminal = Station("고속터미널역")
        val station_banpo = Station("반포역")

        station_expressBusTerminal.updateLineStation(LineStation(Line("연두색","7호선"),station_expressBusTerminal,station_banpo,
            Distance(10)))
        station_expressBusTerminal.updateLineStation(LineStation(Line("초록색","2호선"),station_expressBusTerminal,station_banpo,
            Distance(15)))
        station_expressBusTerminal.updateLineStation(LineStation(Line("주황색","3호선"),station_expressBusTerminal,station_banpo,
            Distance(20)))
        stationRepository.save(station_expressBusTerminal)
    }

    private fun 지하철역_조회() = stationRepository.findById(1L).get()

    private fun 지하철역_데이터_체크(actual: Station, name: String) {
        assertAll(
            { assertThat(actual.name).isEqualTo(name) },
            { assertThat(actual.id).isNotNull() },
            { assertThat(actual.createdDate).isNotNull() },
            { assertThat(actual.modifiedDate).isNotNull() },
        )
    }
}
